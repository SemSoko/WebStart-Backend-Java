package com.semsoko.webstartbackend.user.dto;

import java.util.List;

public class UserProfileResponse {
    private String id;
    private String username;
    private List<String> roles;

    public UserProfileResponse(){

    }

    public UserProfileResponse(
        String id,
        String username,
        List<String> roles
    ){
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public List<String> getRoles(){
        return this.roles;
    }

    public void setRoles(List<String> roles){
        this.roles = roles;
    }
}
