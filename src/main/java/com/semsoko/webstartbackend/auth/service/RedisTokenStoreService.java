package com.semsoko.webstartbackend.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.semsoko.webstartbackend.auth.model.RefreshTokenMetadata;
import com.semsoko.webstartbackend.auth.model.TokenClaims;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

import java.util.Set;
import java.util.UUID;

@Service
public class RedisTokenStoreService implements TokenStoreService{
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisTokenStoreService(
            @Qualifier("redisTemplate")RedisTemplate<String, String> redisTempalte,
            ObjectMapper objectMapper
    ){
        this.redisTemplate = redisTempalte;
        this.objectMapper = objectMapper;
    }

    private String getRefreshTokenKey(UUID userId, String jti){
        return String.format("refresh:%s%s", userId, jti);
    }

    private String getRefreshIndexKey(UUID userId){
        return String.format("refresh:index:%s", userId);
    }

    private String getBlackListKey(String jti){
        return "blacklist:" + jti;
    }

    @Override
    public void storeRefreshToken(
            TokenClaims claims,
            RefreshTokenMetadata metadata
    ){
        try{
            UUID userId = claims.getUserId();
            String token = claims.getJti();
            Duration ttl = Duration.between(Instant.now(), claims.getExp());

            String tokenKey = getRefreshTokenKey(userId, token);
            String metadataJson = objectMapper.writeValueAsString(metadata);

            redisTemplate.opsForValue().set(tokenKey, metadataJson, ttl);

            String indexKey = getRefreshIndexKey(userId);
            redisTemplate.opsForSet().add(indexKey, tokenKey);
        }catch(Exception e){
            throw new RuntimeException("Failed to store refresh token in Redis", e);
        }
    }

    @Override
    public boolean isRefreshTokenValid(
            UUID userId,
            String jti
    ){
        String tokenKey = getRefreshTokenKey(userId, jti);

        return Boolean.TRUE.equals(redisTemplate.hasKey(tokenKey));
    }

    @Override
    public void deleteRefreshToken(UUID userId, String jti){
        String tokenKey = getRefreshTokenKey(userId, jti);
        String indexKey = getRefreshIndexKey(userId);

        redisTemplate.delete(tokenKey);
        redisTemplate.opsForSet().remove(indexKey, tokenKey);
    }

    @Override
    public void deleteAllRefreshTokensForUser(UUID userId){
        String indexKey = getRefreshIndexKey(userId);

        Set<String> tokenKeys = redisTemplate.opsForSet().members(indexKey);

        if(tokenKeys != null && !tokenKeys.isEmpty()){
            redisTemplate.delete(tokenKeys);
        }

        redisTemplate.delete(indexKey);
    }

    @Override
    public void blacklistAccessToken(String jti, Instant expiration){
        String key = getBlackListKey(jti);
        Duration ttl = Duration.between(Instant.now(), expiration);

        redisTemplate.opsForValue().set(key, "1", ttl);
    }

    @Override
    public boolean isAccessTokenBlacklisted(String jti){
        String key = getBlackListKey(jti);

        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
