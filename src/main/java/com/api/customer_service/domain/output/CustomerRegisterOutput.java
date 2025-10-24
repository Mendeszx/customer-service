package com.api.customer_service.domain.output;

import com.api.customer_service.model.CustomerRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterOutput {

    private UUID customerId;
    private LocalDate createdAt;
    private CustomerRegisterResponse.MessageEnum message;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public CustomerRegisterResponse.MessageEnum getMessage() {
        return message;
    }

    public void setMessage(CustomerRegisterResponse.MessageEnum message) {
        this.message = message;
    }
}
