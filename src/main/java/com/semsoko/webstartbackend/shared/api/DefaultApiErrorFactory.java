package com.semsoko.webstartbackend.shared.api;

import org.springframework.stereotype.Component;

/**
 * Standard-Spring-Implementierung von {@link ApiErrorFactory}.
 *
 * Erstellt {@link ApiError}-Objekte basierend auf den übergebenen Parametern.
 * Wird als Spring-Bean registriert und kann in Services, Controllern oder
 * Exception-Handlern injiziert werden.
 */

@Component
public class DefaultApiErrorFactory implements ApiErrorFactory{
    @Override
    public ApiError create(String code, String message){
        return new ApiError(code, message);
    }
}
