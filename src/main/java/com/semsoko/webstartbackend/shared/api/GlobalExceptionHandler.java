package com.semsoko.webstartbackend.shared.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Zentraler Exception-Handler für alle REST-Controller.
 *
 * Wandelt geworfene Exceptions in standardisierte {@link ApiResponse}
 * Objekte um und stellt damit sicher, dass Fehler im gesamten API
 * einheitlich strukturiert sind.
 *
 * Dieser Handler verwendet {@link ApiResponseFactory}, um Antworten zu
 * erzeugen. Dadurch kann die Erzeugungslogik einfach getestet oder
 * angepasst werden.
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ApiResponseFactory apiResponseFactory;

    public GlobalExceptionHandler(ApiResponseFactory apiResponseFactory){
        this.apiResponseFactory = apiResponseFactory;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponseFactory.error("VALIDATION_ERROR", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiResponseFactory.error("INTERNAL_ERROR", "Ein unerwarteter Fehler ist aufgetreten"));
    }
}
