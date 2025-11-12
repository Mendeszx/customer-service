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
public class ConsentModel {

    private UUID consentId;
    private UUID customerId;
    private String version;
    private boolean accept;
}
