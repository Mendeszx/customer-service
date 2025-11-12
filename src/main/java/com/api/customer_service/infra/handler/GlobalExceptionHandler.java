package com.api.customer_service.infra.handler;

import com.api.customer_service.domain.exception.CustomerConflictException;
import com.api.customer_service.model.ErrorResponse;
import com.api.customer_service.model.FieldErrorResponse;
import com.api.customer_service.model.FieldErrorResponseDetailsInner;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
    public ResponseEntity<FieldErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldErrorResponseDetailsInner> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> createFieldErrorResponseDetailsInner(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        var body = createFieldErrorResponse(details, FieldErrorResponse.MessageEnum.INVALID_DATA);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerConflictException.class)
    public ResponseEntity<FieldErrorResponse> handleValidationExceptions(CustomerConflictException ex) {

        var details = createFieldErrorResponseDetailsInner(ex.getField(), ex.getStatus());

        var body = createFieldErrorResponse(List.of(details), FieldErrorResponse.MessageEnum.DUPLICATE_ENTRY);

        return new ResponseEntity<>(body, ex.getHttpStatus());
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

    private FieldErrorResponseDetailsInner createFieldErrorResponseDetailsInner(String field, String status) {
        return new FieldErrorResponseDetailsInner()
                .field(field)
                .status(status);
    }

    private FieldErrorResponse createFieldErrorResponse(List<FieldErrorResponseDetailsInner> details, FieldErrorResponse.MessageEnum messageEnum) {
        return new FieldErrorResponse()
                .details(details)
                .message(messageEnum);
    }

    private ErrorResponse createErrorResponse(Exception exception, HttpServletRequest request) {
        return new ErrorResponse()
                .timestamp(LocalDate.now().atTime(LocalTime.now(ZoneId.systemDefault())).toString())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .traceId(UUID.fromString(request.getHeader(requestIdHeader)));
    }
}
