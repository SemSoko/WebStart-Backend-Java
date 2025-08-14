package com.semsoko.webstartbackend.shared.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    public PasswordHasher(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Hasht ein Klartext-Passwort mit BCrypt.
     *
     * @param plainPassword Das zu hashende Passwort
     * @return Der erzeugte Hash
     */
    public String hashPassword(String plainPassword){
        return passwordEncoder.encode(plainPassword);
    }

    /**
     * Überprüft, ob ein gegebenes Passwort zum gespeicherten Hash passt.
     *
     * @param rawPassword       Klartext-Passwort (z.B. aus Login-Formular)
     * @param hashedPassword    Gespeicherter Hash (z. B. aus DB)
     * @return true, wenn das Passwort gültig ist, sonst false
     */
    public boolean verifyPassword(String rawPassword, String hashedPassword){
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

}
