package com.api.customer_service.app.service;

import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;

import java.util.UUID;

public interface CustomerService {

    CustomerRegisterResponse createCustomer(CustomerRegisterRequest customerRegisterRequest, String xClientId, UUID xRequestId);
}
