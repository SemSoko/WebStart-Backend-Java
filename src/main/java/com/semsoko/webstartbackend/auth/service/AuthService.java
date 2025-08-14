package com.semsoko.webstartbackend.auth.service;

import com.semsoko.webstartbackend.auth.dto.*;

public interface AuthService {
    /**
     * Authentifiziert einen Benutzer und gibt ein Token-Paar zurück.
     */
    LoginResponse login(LoginRequest request);

    /**
     * Erstellt ein neues Token-Paar auf Basis eines gültigen Refresh Tokens.
     */
    RefreshTokenResponse refresh(RefreshTokenRequest request);

    /**
     * Führt einen Logout durch (aktuelle oder alle Sitzungen).
     *
     * @param request           Logout-Request mit optionalem Flag für allDevices
     * @param accessTokenJti    JWT-ID des Access Tokens zur Blacklist
     */
    void logout(LogoutRequest request, String accessTokenJti);
}
