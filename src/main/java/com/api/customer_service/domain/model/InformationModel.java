package com.api.customer_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationModel {

    private UUID customerId;
    private UUID keycloakId;
    private String email;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private String heardAbout;
    private String country;
    private String timezone;
}
