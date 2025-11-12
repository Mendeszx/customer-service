package com.api.customer_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private UUID accountId;
    private UUID customerId;
    private boolean active;
    private String accountType;
}
