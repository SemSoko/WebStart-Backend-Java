package com.semsoko.webstartbackend.auth.dto;

import java.time.Instant;

public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Instant accessTokenExpiration;
    private Instant refreshTokenExpiration;
    private String tokenType = "Bearer";

    public RefreshTokenResponse(){
        
    }

    public RefreshTokenResponse(
            String accessToken,
            String refreshToken,
            Instant accessTokenExpiration,
            Instant refreshTokenExpiration
    ){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public String getRefreshToken(){
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public Instant getAccessTokenExpiration(){
        return this.accessTokenExpiration;
    }

    public void setAccessTokenExpiration(Instant accessTokenExpiration){
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public Instant getRefreshTokenExpiration(){
        return this.refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(Instant refreshTokenExpiration){
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String getTokenType(){
        return tokenType;
    }

    public void setTokenType(String tokenType){
        this.tokenType = tokenType;
    }
}
