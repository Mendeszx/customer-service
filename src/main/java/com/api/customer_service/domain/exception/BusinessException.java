package com.api.customer_service.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BusinessException(String message) {
        super(String.format("Business error: '%s'", message));
        this.message = message;
    }
}
