package com.api.customer_service.infra.handler;

import com.api.customer_service.model.CustomerRegisterErrorResponse;
import com.api.customer_service.model.CustomerRegisterErrorResponseDetailsInner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerRegisterErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<CustomerRegisterErrorResponseDetailsInner> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::createCustomerRegisterErrorResponseDetailsInner)
                .collect(Collectors.toList());

        CustomerRegisterErrorResponse body = new CustomerRegisterErrorResponse();
        body.setDetails(details);
        body.setMessage(CustomerRegisterErrorResponse.MessageEnum.INVALID_DATA);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private CustomerRegisterErrorResponseDetailsInner createCustomerRegisterErrorResponseDetailsInner(FieldError fieldError) {
        CustomerRegisterErrorResponseDetailsInner customerRegisterErrorResponseDetailsInner = new CustomerRegisterErrorResponseDetailsInner();
        customerRegisterErrorResponseDetailsInner.setField(fieldError.getField());
        customerRegisterErrorResponseDetailsInner.setStatus(fieldError.getDefaultMessage());

        return customerRegisterErrorResponseDetailsInner;
    }
}
