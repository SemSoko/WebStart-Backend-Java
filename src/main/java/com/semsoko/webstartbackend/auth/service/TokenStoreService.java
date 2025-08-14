package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.model.RefreshTokenMetadata;
import com.semsoko.webstartbackend.auth.model.TokenClaims;

import java.time.Instant;
import java.util.UUID;

public interface TokenStoreService {
    /**
     * === Refresh Token Store ===
     */
    void storeRefreshToken(TokenClaims claims, RefreshTokenMetadata metadata);
    boolean isRefreshTokenValid(UUID userId, String jti);
    void deleteRefreshToken(UUID userId, String jti);
    void deleteAllRefreshTokensForUser(UUID userId);

    /**
     * === Access Token Blacklist ===
     */
    void blacklistAccessToken(String jti, Instant expiration);
    boolean isAccessTokenBlacklisted(String jti);
}
