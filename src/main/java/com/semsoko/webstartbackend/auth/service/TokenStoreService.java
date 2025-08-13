package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.model.RefreshTokenMetadata;
import com.semsoko.webstartbackend.auth.model.TokenClaims;

import java.time.Instant;

public interface TokenStoreService {
    /**
     * === Refresh Token Store ===
     */
    void storeRefreshToken(TokenClaims claims, RefreshTokenMetadata metadata);
    boolean isRefreshTokenValid(String userId, String jti);
    void deleteRefreshToken(String userId, String jti);
    void deleteAllRefreshTokensForUser(String userId);

    /**
     * === Access Token Blacklist ===
     */
    void blacklistAccessToken(String jti, Instant expiration);
    boolean isAccessTokenBlacklisted(String jti);
}
