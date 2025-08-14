package com.semsoko.webstartbackend.auth.model;

import java.util.List;

public class User {
    private String id;
    private String username;
    private String hashedPassword;
    private List<Role> roles;

    public User(){

    }

    public User(
            String id,
            String username,
            String hashedPassword,
            List<Role> roles
    ){
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
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

    public String getHashedPassword(){
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword){
        this.hashedPassword = hashedPassword;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
}
