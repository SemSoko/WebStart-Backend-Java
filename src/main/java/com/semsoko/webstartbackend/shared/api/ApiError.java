package com.semsoko.webstartbackend.shared.api;

/**
 * Datenobjekt, das strukturierte Fehlerinformationen für API-Antworten enthält.
 *
 * Besteht aus einem Fehlercode und einer dazugehörigen Nachricht.
 * Kann optional in {@link ApiResponse} eingebettet werden, wenn {@code success} false ist.
 */

public class ApiError {
    private final String code;
    private final String message;

    /**
     * Erstellt ein neues ApiError-Objekt mit Code und Nachricht.
     *
     * @param code    eindeutiger Fehlercode (z. B. "VALIDATION_ERROR")
     * @param message beschreibende Fehlermeldung
     */
    public ApiError(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
