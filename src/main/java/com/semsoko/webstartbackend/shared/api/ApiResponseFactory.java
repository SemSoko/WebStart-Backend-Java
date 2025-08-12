package com.semsoko.webstartbackend.shared.api;

/**
 * Factory-Interface zur Erstellung standardisierter API-Antwortobjekte.
 *
 * Implementierungen dieses Interfaces ermöglichen eine konsistente Erzeugung
 * von Erfolgs- und Fehlerantworten in der gesamten Anwendung.
 * Durch den Einsatz einer Factory kann diese Logik in Unit-Tests leicht
 * gemockt werden, um vorhersagbare API-Antworten zu simulieren.
 */

public interface ApiResponseFactory {
    <T> ApiResponse<T> success(T data);
    <T> ApiResponse<T> error(String code, String message);
}
