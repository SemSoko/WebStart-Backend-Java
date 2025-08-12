package com.semsoko.webstartbackend.shared.api;

import org.springframework.stereotype.Component;


/**
 * Standard-Spring-Implementierung von {@link ApiResponseFactory}.
 *
 * Nutzt {@link ApiErrorFactory}, um strukturierte Fehlerobjekte zu erzeugen.
 * Diese Klasse wird als Spring-Bean registriert und kann in Controllern,
 * Services oder Exception-Handlern injiziert werden, um einheitliche
 * API-Antworten zu erstellen.
 */

@Component
public class DefaultApiResponseFactory implements ApiResponseFactory{
    private final ApiErrorFactory apiErrorFactory;

    public DefaultApiResponseFactory(ApiErrorFactory apiErrorFactory){
        this.apiErrorFactory = apiErrorFactory;
    }

    @Override
    public <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(true, data, null);
    }

    @Override
    public <T> ApiResponse<T> error(String code, String message){
        return new ApiResponse<>(false, null, apiErrorFactory.create(code, message));
    }
}
