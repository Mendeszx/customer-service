package com.api.customer_service.app.service.impl;

import com.api.customer_service.app.mapper.CustomerRegisterMapper;
import com.api.customer_service.app.service.CustomerService;
import com.api.customer_service.app.usecase.CustomerUseCase;
import com.api.customer_service.domain.input.CustomerRegisterInput;
import com.api.customer_service.model.CustomerRegisterRequest;
import com.api.customer_service.model.CustomerRegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerUseCase customerUseCase;
    private final CustomerRegisterMapper customerRegisterMapper;

    public CustomerServiceImpl(CustomerUseCase customerUseCase, CustomerRegisterMapper customerRegisterMapper) {
        this.customerUseCase = customerUseCase;
        this.customerRegisterMapper = customerRegisterMapper;
    }

    @Override
    public CustomerRegisterResponse createCustomer(CustomerRegisterRequest customerRegisterRequest, String xClientId, UUID xRequestId, String token) {

        CustomerRegisterInput input = CustomerRegisterInput.builder()
                .email(customerRegisterRequest.getEmail())
                .name(customerRegisterRequest.getName())
                .phone(customerRegisterRequest.getPhone())
                .birthDate(customerRegisterRequest.getBirthDate())
                .gender(customerRegisterRequest.getGender().getValue())
                .password(customerRegisterRequest.getPassword())
                .xClientId(xClientId)
                .xRequestId(xRequestId)
                .token(token)
                .build();

        var customerRegisterOutput = customerUseCase.createCustomer(input);

        log.debug("customerRegisterOutput: {}", customerRegisterOutput);

        var customerRegisterResponse = customerRegisterMapper.toResponse(customerRegisterOutput);

        log.info("customerRegisterResponse: {}", customerRegisterResponse);

        return customerRegisterResponse;
    }
}
