package com.semsoko.webstartbackend.shared.api;

/**
 * Generisches Datenobjekt, das eine standardisierte API-Antwort repräsentiert.
 *
 * Enthält immer:
 * {@code success} – Gibt an, ob die Anfrage erfolgreich verarbeitet wurde
 * {@code data} – Nutzdaten bei erfolgreicher Anfrage (kann null sein)
 * {@code error} – Fehlerdetails, falls {@code success} false ist
 *
 * @param <T> Typ der Nutzdaten in der Antwort
 */

public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final ApiError error;

    public ApiResponse(boolean success, T data, ApiError error){
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public boolean isSuccess(){
        return success;
    }

    public T getData(){
        return data;
    }

    public ApiError getError(){
        return error;
    }
}
