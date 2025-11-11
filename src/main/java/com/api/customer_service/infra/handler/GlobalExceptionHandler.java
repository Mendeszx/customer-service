package com.api.customer_service.infra.handler;

import com.api.customer_service.model.CustomerRegisterErrorResponse;
import com.api.customer_service.model.CustomerRegisterErrorResponseDetailsInner;
import com.api.customer_service.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${request.requestId}")
    private String requestIdHeader;

    @Value("${request.clientId}")
    private String clientIdHeader;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception exception, HttpServletRequest request) {

        log.error("Unexpected error occurred | path={} | clientId={} | requestId={}",
                request.getRequestURI(),
                request.getHeader(clientIdHeader),
                request.getHeader(requestIdHeader),
                exception);

        ErrorResponse response = createErrorResponse(exception, request);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private CustomerRegisterErrorResponseDetailsInner createCustomerRegisterErrorResponseDetailsInner(FieldError fieldError) {
        CustomerRegisterErrorResponseDetailsInner customerRegisterErrorResponseDetailsInner = new CustomerRegisterErrorResponseDetailsInner();
        customerRegisterErrorResponseDetailsInner.setField(fieldError.getField());
        customerRegisterErrorResponseDetailsInner.setStatus(fieldError.getDefaultMessage());

        return customerRegisterErrorResponseDetailsInner;
    }

    private ErrorResponse createErrorResponse(Exception exception, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();

        response.setTimestamp(LocalDate.now().atTime(LocalTime.now(ZoneId.systemDefault())).toString());
        response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setMessage(exception.getMessage());
        response.setPath(request.getRequestURI());

        String requestId = request.getHeader(requestIdHeader);

        if (requestId != null && !requestId.isBlank()) {
            response.setTraceId(UUID.fromString(requestId));
        }

        return response;
    }
}
