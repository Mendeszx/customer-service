package com.api.customer_service.app.usecase.impl;

import com.api.customer_service.app.usecase.CustomerUseCase;
import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;
import com.api.customer_service.model.CustomerRegisterResponseDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

    @Override
    public CustomerRegisterResponse createCustomer(CustomerRegisterRequest customerRegisterRequest) {

        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();

        CustomerRegisterResponseDetails customerRegisterResponseDetails = new CustomerRegisterResponseDetails();
        customerRegisterResponseDetails.setCustomerId(UUID.randomUUID());
        customerRegisterResponseDetails.setCreatedAt(LocalDate.now());

        customerRegisterResponse.setDetails(customerRegisterResponseDetails);
        customerRegisterResponse.setMessage(CustomerRegisterResponse.MessageEnum.CUSTOMER_CREATED_SUCCESSFULLY);

        return customerRegisterResponse;
    }
}
