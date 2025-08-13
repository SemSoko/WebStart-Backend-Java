package com.semsoko.webstartbackend.auth.security;

import com.semsoko.webstartbackend.auth.model.TokenClaims;
import com.semsoko.webstartbackend.auth.service.TokenStoreService;
import com.semsoko.webstartbackend.shared.util.JwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtUtil jwtUtil;
    private final TokenStoreService tokenStoreService;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(
            JwtUtil jwtUtil,
            TokenStoreService tokenStoreService,
            ObjectMapper objectMapper
    ){
        this.jwtUtil = jwtUtil;
        this.tokenStoreService = tokenStoreService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        /**
         * Kein Authorization Header oder falsches Format -> weiter
         */
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try{
            /**
             * 1. JWT Claims parsen
             */
            TokenClaims claims = jwtUtil.parseToken(token);

            /**
             * 2. Blacklist pruefen
             */
            if(tokenStoreService.isAccessTokenBlacklisted(claims.getJti())){
                sendUnauthorized(response, "Access token ist blacklisted");
                return;
            }

            /**
             * 3. Authentication im SecurityContext setzen
             */
            List<SimpleGrantedAuthority> authorities = claims.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(claims.getUserId(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception e){
            /**
             * Fehler bei Parsing oder Validation -> 401
             */
            sendUnauthorized(response, "Invalid or expired JWT");
            return;
        }

        /**
         * 4. Weiter in der Filterkette
         */
        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException{
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> errorBody = Map.of(
                "error", "Unauthorized",
                "message", message
        );

        objectMapper.writeValue(response.getOutputStream(), errorBody);
    }
}
