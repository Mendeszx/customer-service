package com.api.customer_service.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {
    public abstract HttpStatus getHttpStatus();
}
