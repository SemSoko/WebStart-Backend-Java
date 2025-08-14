package com.semsoko.webstartbackend.shared.util;

import com.semsoko.webstartbackend.auth.model.TokenClaims;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Standardimplementierung von {@link JwtUtil} unter Verwendung der JJWT-Bibliothek.
 *
 * Diese Klasse unterstuetzt:
 * Erzeugung von Access- und Refresh-Token mit HS256
 * Validierung und Ablaufpruefung
 * Parsing von TokenClaims aus JWT-Strings
 */
@Component
public class JwtUtilImpl implements JwtUtil{
    private final SecretKey secretKey;
    private final long accessTokenExpirationSeconds;
    private final long refreshTokenExpirationSeconds;

    public JwtUtilImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-expiration-seconds}") long accessTokenExpirationSeconds,
            @Value("${jwt.refresh-token-expiration-seconds}") long refreshTokenExpirationSeconds
    ){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationSeconds = accessTokenExpirationSeconds;
        this.refreshTokenExpirationSeconds = refreshTokenExpirationSeconds;
    }

    @Override
    public String generateAccessToken(TokenClaims claims){
        return buildToken(claims, accessTokenExpirationSeconds);
    }

    @Override
    public String generateRefreshToken(TokenClaims claims){
        return buildToken(claims, refreshTokenExpirationSeconds);
    }

    private String buildToken(TokenClaims claims, long expirationSeconds){
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationSeconds);

        return Jwts.builder()
                .setId(claims.getJti() != null ? claims.getJti() : UUID.randomUUID().toString())
                .setSubject(claims.getUserId().toString())
                .claim("roles", claims.getRoles())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public TokenClaims parseToken(String token) throws JwtException{
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);

        Claims body = jwsClaims.getBody();

        return new TokenClaims(
                UUID.fromString(body.getSubject()),
                body.get("roles", List.class),
                body.getId(),
                body.getIssuedAt().toInstant(),
                body.getExpiration().toInstant()
        );
    }

    @Override
    public boolean isTokenExpired(String token){
        try{
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        }catch(JwtException e){
            /**
             * Bei Fehler gilt Token als "abgelaufen/ungueltig"
             */
            return true;
        }
    }

    @Override
    public boolean isTokenValid(String token){
        try{
            parseToken(token);
            return !isTokenExpired(token);
        }catch(JwtException e){
            return false;
        }
    }
}
