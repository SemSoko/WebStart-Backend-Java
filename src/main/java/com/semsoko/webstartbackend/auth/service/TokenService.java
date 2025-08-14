package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.model.TokenClaims;

public interface TokenService {
    /**
     * Erstellt ein neues Access Token basierend auf den übergebenen Claims.
     */
    String generateAccessToken(TokenClaims claims);

    /**
     * Erstellt ein neues Refresh Token basierend auf den übergebenen Claims.
     */
    String generateRefreshToken(TokenClaims claims);

    /**
     * Parst ein JWT und extrahiert daraus die enthaltenen Claims.
     */
    TokenClaims parseToken(String token);
}
