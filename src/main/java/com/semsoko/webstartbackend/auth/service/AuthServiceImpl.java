package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.dto.*;

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
        return null;
    }

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest request){
        return null;
    }

    @Override
    public void logout(LogoutRequest request, String accessTokenJti){
        
    }
}
