package com.semsoko.webstartbackend.auth.model;

import java.time.Instant;

public class RefreshTokenMetadata {
    private Instant issuedAt;
    private String userAgent;
    private String ipAddress;

    public RefreshTokenMetadata(){}

    public RefreshTokenMetadata(
            Instant issuedAt,
            String userAgent,
            String ipAddress
    ){
        this.issuedAt = issuedAt;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
    }

    public Instant getIssuedAt(){
        return this.issuedAt;
    }

    public void setIssuedAt(Instant issuedAt){
        this.issuedAt = issuedAt;
    }

    public String getUserAgent(){
        return this.userAgent;
    }

    public void setUserAgent(String userAgent){
        this.userAgent = userAgent;
    }

    public String getIpAddress(){
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString(){
        return "RefreshTokenMetadata{" +
                "issuedAt=" + issuedAt +
                ", userAgent='" + userAgent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
