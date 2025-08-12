package com.semsoko.webstartbackend.shared.api;

/**
 * Factory-Interface zur Erstellung von {@link ApiError}-Objekten.
 *
 * Ermöglicht die zentrale Erzeugung von Fehlerobjekten, um Format und Struktur
 * einheitlich zu halten und in Tests einfach mocken zu können.
 */

public interface ApiErrorFactory {
    /**
     * Erzeugt ein neues {@link ApiError}-Objekt mit dem angegebenen Code und der Nachricht.
     *
     * @param code    eindeutiger Fehlercode (z. B. "NOT_FOUND")
     * @param message beschreibende Fehlermeldung
     * @return erzeugtes {@link ApiError}-Objekt
     */
    ApiError create(String code, String message);
}
