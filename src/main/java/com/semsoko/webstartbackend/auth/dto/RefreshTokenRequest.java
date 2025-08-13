package com.semsoko.webstartbackend.auth.dto;

public class RefreshTokenRequest {
    private String refreshToken;

    public RefreshTokenRequest(){

    }

    public RefreshTokenRequest(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken(){
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
