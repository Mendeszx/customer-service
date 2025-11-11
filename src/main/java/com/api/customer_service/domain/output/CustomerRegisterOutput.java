package com.api.customer_service.domain.output;

import com.api.customer_service.model.CustomerRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterOutput {

    private UUID customerId;
    private LocalDateTime createdAt;
    private CustomerRegisterResponse.MessageEnum message;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CustomerRegisterResponse.MessageEnum getMessage() {
        return message;
    }

    public void setMessage(CustomerRegisterResponse.MessageEnum message) {
        this.message = message;
    }
}
