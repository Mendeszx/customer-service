package com.api.customer_service.domain.usecase.impl;

import com.api.customer_service.domain.usecase.CustomerUseCase;
import com.api.customer_service.domain.input.CustomerRegisterInput;
import com.api.customer_service.domain.output.CustomerRegisterOutput;
import com.api.customer_service.model.CustomerRegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.UUID;

@Slf4j
@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

    @Override
    public CustomerRegisterOutput createCustomer(CustomerRegisterInput customerRegisterInput) {

        log.info("CustomerRegisterRequest: {}", customerRegisterInput);

        return CustomerRegisterOutput.builder()
                .customerId(UUID.randomUUID())
                .createdAt(LocalDate.now().atTime(LocalTime.now(ZoneId.systemDefault())))
                .message(CustomerRegisterResponse.MessageEnum.CUSTOMER_CREATED_SUCCESSFULLY)
                .build();
    }
}
