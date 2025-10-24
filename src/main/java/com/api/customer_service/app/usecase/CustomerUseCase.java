package com.api.customer_service.app.usecase;


import com.api.customer_service.domain.input.CustomerRegisterInput;
import com.api.customer_service.domain.output.CustomerRegisterOutput;

public interface CustomerUseCase {

    CustomerRegisterOutput createCustomer(CustomerRegisterInput customerRegisterInput);
}
