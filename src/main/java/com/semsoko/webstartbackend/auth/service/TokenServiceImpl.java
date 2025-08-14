package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.model.TokenClaims;
import com.semsoko.webstartbackend.shared.util.JwtUtil;

import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService{
    private final JwtUtil jwtUtil;

    public TokenServiceImpl(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generateAccessToken(TokenClaims claims){
        return jwtUtil.generateAccessToken(claims);
    }

    @Override
    public String generateRefreshToken(TokenClaims claims){
        return jwtUtil.generateRefreshToken(claims);
    }

    @Override
    public TokenClaims parseToken(String token){
        return jwtUtil.parseToken(token);
    }
}
