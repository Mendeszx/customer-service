package com.api.customer_service.app.mapper;

import com.api.customer_service.domain.output.CustomerRegisterOutput;
import com.api.customer_service.model.CustomerRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRegisterMapper {

    @Mapping(target = "details.customerId", source = "customerId")
    @Mapping(target = "details.createdAt", source = "createdAt")
    @Mapping(target = "message", source = "message")
    CustomerRegisterResponse toResponse(CustomerRegisterOutput output);
}
