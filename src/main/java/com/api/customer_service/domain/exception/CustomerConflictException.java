package com.api.customer_service.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CustomerConflictException extends RuntimeException {

    private final String field;
    private final String status;
    private final HttpStatus httpStatus = HttpStatus.CONFLICT;

    public CustomerConflictException(String field, String status) {
        super(String.format("Duplicate entry for field '%s': '%s'", field, status));
        this.field = field;
        this.status = status;
    }
}
