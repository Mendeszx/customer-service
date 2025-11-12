package com.api.customer_service.domain.output;

import com.api.customer_service.model.CustomerRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterOutput {

    private UUID customerId;
    private LocalDateTime createdAt;
    private CustomerRegisterResponse.MessageEnum message;
}
