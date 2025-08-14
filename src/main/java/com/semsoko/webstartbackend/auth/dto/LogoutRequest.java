package com.semsoko.webstartbackend.auth.dto;

public class LogoutRequest {
    private String refreshToken;
    private boolean allDevices;

    public LogoutRequest(){

    }

    public LogoutRequest(String refreshToken, boolean allDevices){
        this.refreshToken = refreshToken;
        this.allDevices = allDevices;
    }

    public String getRefreshToken(){
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public boolean isAllDevices(){
        return this.allDevices;
    }

    public void setAllDevices(boolean allDevices){
        this.allDevices = allDevices;
    }
}
