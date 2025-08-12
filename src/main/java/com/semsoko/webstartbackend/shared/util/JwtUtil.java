package com.semsoko.webstartbackend.shared.util;

import com.semsoko.webstartbackend.auth.model.TokenClaims;

import io.jsonwebtoken.JwtException;

/**
 * Schnittstelle für Hilfsmethoden zur Arbeit mit JSON Web Tokens (JWT).
 *
 * Diese Utility-Schnittstelle definiert die Kernoperationen zum
 * Erzeugen, Validieren und Auslesen von JWTs im Rahmen der
 * Authentifizierungs- und Autorisierungslogik.
 *
 * Durch die Verwendung eines Interfaces kann die Implementierung
 * (z. B. mit unterschiedlichen Signaturalgorithmen oder Bibliotheken)
 * leicht ausgetauscht oder für Tests gemockt werden.
 */

public interface JwtUtil {
    /**
     * Erzeugt ein signiertes JWT basierend auf den übergebenen Claims.
     *
     * @params claims
     * Objekt mit den zu speichernden Tokeninformationen wie Benutzer-ID, Rollen,
     * Ausstellungs- und Ablaufzeit.
     * @return  Signierter JWT-String.
     */
    String generateToken(TokenClaims claims);

    /**
     * Parst einen JWT-String und gibt die enthaltenen Claims als
     * {@link TokenClaims}-Objekt zurück.
     *
     * @param token Der zu parsende JWT-String.
     * @return      {@link TokenClaims} mit den aus dem Token gelesenen Werten.
     * @throws      io.jsonwebtoken.JwtException wenn der Token ungültig oder manipuliert ist.
     */
    TokenClaims parseToken(String token);

    /**
     * Prüft, ob ein übergebener JWT-String gültig ist.
     *
     * Ein Token ist gültig, wenn:
     * die Signatur korrekt ist,
     * das Ablaufdatum nicht überschritten wurde,
     * und keine Manipulation erkannt wurde.
     *
     * @param token Der zu prüfende JWT-String.
     * @return {@code true}, wenn der Token gueltig ist, andernfalls {@code false}.
     */
    boolean isTokenValid(String token);
}
