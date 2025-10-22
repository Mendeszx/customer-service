package com.api.customer_service.app.usecase;


import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;

public interface CustomerUseCase {

    CustomerRegisterResponse createCustomer(CustomerRegisterRequest customerRegisterRequest);
}
