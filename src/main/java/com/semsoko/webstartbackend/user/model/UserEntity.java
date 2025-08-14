package com.semsoko.webstartbackend.user.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String hashedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<Role> roles;

    /**
     * Default-Konstruktor fuer JPA
     */
    public UserEntity(){

    }


    public UserEntity(
            UUID id,
            String username,
            String hashedPassword,
            List<Role> roles
    ){
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.roles = roles;
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
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

    public List<Role> getRoles(){
        return this.roles;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
}
