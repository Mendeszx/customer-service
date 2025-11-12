package com.api.customer_service.app.controller;

import com.api.customer_service.api.CustomersApi;
import com.api.customer_service.app.service.CustomerService;
import com.api.customer_service.model.CustomerAccountPatchRequest;
import com.api.customer_service.model.CustomerAccountResponse;
import com.api.customer_service.model.CustomerInformationPatchRequest;
import com.api.customer_service.model.CustomerInformationResponse;
import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerRegisterResponse> createCustomer(String xClientId, UUID xRequestId, CustomerRegisterRequest customerRegisterRequest) {

        var response = customerService.createCustomer(customerRegisterRequest, xClientId, xRequestId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<CustomerAccountResponse>> getCustomerAccountByCustomerId(UUID customerId, String xClientId, UUID xRequestId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<CustomerInformationResponse> getCustomerInformationByCustomerId(UUID customerId, String xClientId, UUID xRequestId) {

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<Void> patchCustomerAccountByCustomerId(UUID customerId, String xClientId, UUID xRequestId, CustomerAccountPatchRequest customerAccountPatchRequest) {

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> patchCustomerInformationByCustomerId(UUID customerId, String xClientId, UUID xRequestId, CustomerInformationPatchRequest customerInformationPatchRequest) {

        return ResponseEntity.noContent().build();
    }
}
