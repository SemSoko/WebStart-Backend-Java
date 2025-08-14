package com.semsoko.webstartbackend.auth.controller;

import com.semsoko.webstartbackend.auth.dto.*;
import com.semsoko.webstartbackend.auth.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    private String extractTokenFromHeader(String header){
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        throw new IllegalArgumentException("Invalid Authorization header");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ){
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(
            @RequestBody RefreshTokenRequest request
    ){
        RefreshTokenResponse response = authService.refresh(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestBody LogoutRequest request,
            @RequestHeader(name = "Authorization") String authHeader
    ){
        String accessToken = extractTokenFromHeader(authHeader);
        authService.logout(request, accessToken);

        return ResponseEntity.noContent().build();
    }
}
