package com.api.customer_service.infra.controller;

import com.api.customer_service.api.CustomersApi;
import com.api.customer_service.app.usecase.CustomerUseCase;
import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CustomerController implements CustomersApi {

    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @Override
    public ResponseEntity<CustomerRegisterResponse> createCustomer(String xClientId, CustomerRegisterRequest customerRegisterRequest, UUID xRequestId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(customerUseCase.createCustomer(customerRegisterRequest));
    }
}
