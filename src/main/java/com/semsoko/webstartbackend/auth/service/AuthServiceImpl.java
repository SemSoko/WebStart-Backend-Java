package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.dto.*;
import com.semsoko.webstartbackend.auth.model.RefreshTokenMetadata;
import com.semsoko.webstartbackend.auth.model.TokenClaims;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final TokenService tokenService;
    private final TokenStoreService tokenStoreService;

    public AuthServiceImpl(
            TokenService tokenService,
            TokenStoreService tokenStoreService
    ){
        this.tokenService = tokenService;
        this.tokenStoreService = tokenStoreService;
    }

    @Override
    public LoginResponse login(LoginRequest request){
        /**
         * 1. Benutzer validieren (Platzhalter fuer UserService)
         */
        String username = request.getUsername();
        String password = request.getPassword();

        /**
         * TODO: Ersetze durch reale Benutzervalidierung
         */
        if(!"user".equals(username) || !"pass".equals(password)){
            throw new RuntimeException("Invalid credentials");
        }

        /**
         * 2. TokenClaims erzeugen
         */
        TokenClaims claims = new TokenClaims();
        /**
         * TODO: Mit echter User-ID ersetzen
         */
        claims.setUserId("123");
        /**
         * TODO: Echte Rolle setzen
         */
        claims.setRoles(List.of("USER"));

        /**
         * 3. Token generieren
         */
        String accessToken = tokenService.generateAccessToken(claims);
        String refreshToken = tokenService.generateRefreshToken(claims);

        /**
         * 4. Claims mit Zeitdaten aktualisieren (fuer Redis + Response)
         */
        TokenClaims parsedRefreshClaims = tokenService.parseToken(refreshToken);

        /**
         * 5. Refresh-Token in Redis speichern
         */
        RefreshTokenMetadata metadata = new RefreshTokenMetadata();
        tokenStoreService.storeRefreshToken(parsedRefreshClaims, metadata);

        /**
         * 6. Response zurueckgeben
         */
        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setAccessTokenExpiration(claims.getExp());
        response.setRefreshTokenExpiration(parsedRefreshClaims.getExp());

        return response;
    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest request){
        String refreshToken = request.getRefreshToken();

        /**
         * 1. TokenClaims aus Refresh-Token extrahieren
         */
        TokenClaims oldClaims = tokenService.parseToken(refreshToken);
        String userId = oldClaims.getUserId();
        String jti = oldClaims.getJti();

        /**
         * 2. Redis pruefen -> existiert der Token?
         */
        if(!tokenStoreService.isRefreshTokenValid(userId, jti)){
            throw new RuntimeException("Invalid or expired refresh token");
        }

        /**
         * 3. Alten Refresh Token loeschen
         */
        tokenStoreService.deleteRefreshToken(userId, jti);

        /**
         * 4. Neue Claims erzeugen
         */
        TokenClaims newClaims = new TokenClaims();
        newClaims.setUserId(userId);
        /**
         * Alte Rolle uebernehmen
         */
        newClaims.setRoles(oldClaims.getRoles());

        /**
         * 5. Neue Tokens erzeugen
         */
        String newAcessToken = tokenService.generateAccessToken(newClaims);
        String newRefreshToken = tokenService.generateRefreshToken(newClaims);

        /**
         * 6. Neue Claims parsen (um exp fuer Redis + Response zu bekommen)
         */
        TokenClaims parsedRefreshClaims = tokenService.parseToken(newRefreshToken);

        /**
         * 7. Refresh Token in Redis speichern
         */
        RefreshTokenMetadata metadata = new RefreshTokenMetadata();
        /**
         * userAgent / IP einfuegen
         */
        tokenStoreService.storeRefreshToken(parsedRefreshClaims, metadata);

        /**
         * 8. Response zurueckgeben
         */
        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setAccessToken(newAcessToken);
        response.setRefreshToken(newRefreshToken);
        response.setAccessTokenExpiration(newClaims.getExp());
        response.setRefreshTokenExpiration(parsedRefreshClaims.getExp());

        return response;
    }

    @Override
    public void logout(LogoutRequest request, String accessTokenJti){
        TokenClaims accessTokenClaims = tokenService.parseToken("Bearer " + accessTokenJti);
        String userId = accessTokenClaims.getUserId();
        Instant accessTokenExp = accessTokenClaims.getExp();

        if(request.isAllDevices()){
            /**
             * 1. Alle Refresh Tokens des Benutzers loeschen
             */
            tokenStoreService.deleteAllRefreshTokensForUser(userId);
        }else{
            /**
             * 2. Nur das uebermittelte Refresh Token loeschen
             */
            TokenClaims refreshClaims = tokenService.parseToken(request.getRefreshToken());
            tokenStoreService.deleteRefreshToken(refreshClaims.getUserId(), refreshClaims.getJti());
        }

        /**
         * 3. Access Token in die Blacklist eintragen
         */
        tokenStoreService.blacklistAccessToken(accessTokenJti, accessTokenExp);
    }
}
