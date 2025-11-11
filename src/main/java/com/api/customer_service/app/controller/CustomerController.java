package com.api.customer_service.app.controller;

import com.api.customer_service.api.CustomersApi;
import com.api.customer_service.app.service.CustomerService;
import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerRegisterResponse> createCustomer(String xClientId, UUID xRequestId, CustomerRegisterRequest customerRegisterRequest) {

        var response = customerService.createCustomer(customerRegisterRequest, xClientId, xRequestId, "");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
