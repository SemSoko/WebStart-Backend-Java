package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.dto.*;
import com.semsoko.webstartbackend.auth.model.RefreshTokenMetadata;
import com.semsoko.webstartbackend.auth.model.TokenClaims;

import com.semsoko.webstartbackend.shared.util.PasswordHasher;

import com.semsoko.webstartbackend.user.model.Role;
import com.semsoko.webstartbackend.user.model.UserEntity;
import com.semsoko.webstartbackend.user.service.UserService;

import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService{
    private final TokenService tokenService;
    private final TokenStoreService tokenStoreService;
    private final UserService userService;
    private final PasswordHasher passwordHasher;

    public AuthServiceImpl(
            TokenService tokenService,
            TokenStoreService tokenStoreService,
            UserService userService,
            PasswordHasher passwordHasher
    ){
        this.tokenService = tokenService;
        this.tokenStoreService = tokenStoreService;
        this.userService = userService;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public LoginResponse login(LoginRequest request){
        UserEntity user = authenticateUser(request.getUsername(), request.getPassword());
        TokenClaims claims = createTokenClaims(user);

        String accessToken = tokenService.generateAccessToken(claims);
        String refreshToken = tokenService.generateRefreshToken(claims);

        TokenClaims parsedRefreshClaims = tokenService.parseToken(refreshToken);

        tokenStoreService.storeRefreshToken(parsedRefreshClaims, new RefreshTokenMetadata());

        return buildLoginResponse(accessToken, refreshToken, claims, parsedRefreshClaims);
    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest request){
        String refreshToken = request.getRefreshToken();
        TokenClaims oldClaims = tokenService.parseToken(refreshToken);
        UUID userId = oldClaims.getUserId();
        String jti = oldClaims.getJti();

        if(!tokenStoreService.isRefreshTokenValid(userId, jti)){
            throw new RuntimeException("Invalid or expired refresh token");
        }

        tokenStoreService.deleteRefreshToken(userId, jti);

        TokenClaims newClaims = new TokenClaims();
        newClaims.setUserId(userId);
        newClaims.setRoles(oldClaims.getRoles());

        String newAccessToken = tokenService.generateAccessToken(newClaims);
        String newRefreshToken = tokenService.generateRefreshToken(newClaims);

        TokenClaims parsedRefreshClaims = tokenService.parseToken(newRefreshToken);

        tokenStoreService.storeRefreshToken(parsedRefreshClaims, new RefreshTokenMetadata());

        return buildRefreshTokenResponse(newAccessToken, newRefreshToken, newClaims, parsedRefreshClaims);
    }

    @Override
    public void logout(LogoutRequest request, String accessTokenJti){
        TokenClaims accessTokenClaims = tokenService.parseToken("Bearer " + accessTokenJti);
        UUID userId = accessTokenClaims.getUserId();
        Instant accessTokenExp = accessTokenClaims.getExp();

        if(request.isAllDevices()){
            tokenStoreService.deleteAllRefreshTokensForUser(userId);
        }else{
            TokenClaims refreshClaims = tokenService.parseToken(request.getRefreshToken());
            tokenStoreService.deleteRefreshToken(refreshClaims.getUserId(), refreshClaims.getJti());
        }

        tokenStoreService.blacklistAccessToken(accessTokenJti, accessTokenExp);
    }

    /**
     * Hilfsmethoden
     */

    private UserEntity authenticateUser(String username, String password){
        UserEntity user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(!passwordHasher.verifyPassword(password, user.getHashedPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    private TokenClaims createTokenClaims(UserEntity user){
        TokenClaims claims = new TokenClaims();
        claims.setUserId(user.getId());
        claims.setRoles(user.getRoles()
                .stream()
                .map(Role::name)
                .collect(Collectors.toList()));

        return claims;
    }

    private LoginResponse buildLoginResponse(
            String accessToken,
            String refreshToken,
            TokenClaims claims,
            TokenClaims parsedRefreshClaims
    ){
        LoginResponse response = new LoginResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setAccessTokenExpiration(claims.getExp());
        response.setRefreshTokenExpiration(parsedRefreshClaims.getExp());

        return response;
    }

    private RefreshTokenResponse buildRefreshTokenResponse(
            String accessToken,
            String refreshToken,
            TokenClaims claims,
            TokenClaims parsedRefreshClaims
    ){
        RefreshTokenResponse response = new RefreshTokenResponse();

        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setAccessTokenExpiration(claims.getExp());
        response.setRefreshTokenExpiration(parsedRefreshClaims.getExp());

        return response;
    }
}
