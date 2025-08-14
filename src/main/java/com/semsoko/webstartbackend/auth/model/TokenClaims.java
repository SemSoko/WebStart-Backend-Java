package com.semsoko.webstartbackend.auth.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Repräsentiert die Claims, die in einem JWT enthalten sind.
 *
 * Claims sind Informationen über den Benutzer oder das Token selbst,
 * die in einem JWT (JSON Web Token) gespeichert werden. Sie dienen
 * zur Authentifizierung (Nachweis der Identität) und Autorisierung
 * (Zugriffsrechte) eines Benutzers.
 *
 * Diese Klasse kapselt u. a.:
 * Benutzer-ID
 * Rollen des Benutzers
 * JTI (eindeutige Token-ID)
 * Ausstellungszeitpunkt (iat)
 * Ablaufzeitpunkt (exp)
 */

public class TokenClaims {
    /**
     * Eindeutige ID des Benutzers, auf den sich das Token bezieht.
     */
    private UUID userId;

    /**
     * Liste der Rollen, die dem Benutzer zugeordnet sind.
     * Diese werden zur Autorisierungsprüfung verwendet.
     */
    private List<String> roles;

    /**
     * Eindeutige Token-ID (JWT ID, "jti"), um einzelne Tokens
     * identifizieren und ggf. widerrufen zu können.
     */
    private String jti;

    /**
     * Zeitpunkt, an dem das Token ausgestellt wurde ("issued at").
     */
    private Instant iat;

    /**
     * Zeitpunkt, an dem das Token abläuft ("expiration").
     */
    private Instant exp;

    /**
     * Standardkonstruktor (erforderlich für Frameworks wie Jackson).
     */
    public TokenClaims(){

    }

    /**
     * Konstruktor zum Erstellen eines vollständigen TokenClaims-Objekts.
     *
     * @param userId    Benutzer-ID
     * @param roles     Liste der Rollen
     * @param jti       Eindeutige Token-ID
     * @param iat       Ausstellungszeitpunkt
     * @param exp       Ablaufzeitpunkt
     */
    public TokenClaims(
            UUID userId,
            List<String> roles,
            String jti,
            Instant iat,
            Instant exp
    ){
        this.userId = userId;
        this.roles = roles;
        this.jti = jti;
        this.iat = iat;
        this.exp = exp;
    }

    public UUID getUserId(){
        return this.userId;
    }

    public void setUserId(UUID userId){
        this.userId = userId;
    }

    public List<String> getRoles(){
        return this.roles;
    }

    public void setRoles(List<String> roles){
        this.roles = roles;
    }

    public String getJti(){
        return this.jti;
    }

    public void setJti(String jti){
        this.jti = jti;
    }

    public Instant getIat(){
        return this.iat;
    }

    public void setIat(Instant iat){
        this.iat = iat;
    }

    public Instant getExp(){
        return this.exp;
    }

    public void setExp(Instant exp){
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "TokenClaims{" +
                "userId='" + userId + '\'' +
                ", roles=" + roles +
                ", jti='" + jti + '\'' +
                ", iat=" + iat +
                ", exp=" + exp +
                '}';

    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(!(o instanceof TokenClaims)){
            return false;
        }

        TokenClaims that = (TokenClaims) o;

        return Objects.equals(userId, that.userId) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(jti, that.jti) &&
                Objects.equals(iat, that.iat) &&
                Objects.equals(exp, that.exp);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId, roles, jti, iat, exp);
    }
}
