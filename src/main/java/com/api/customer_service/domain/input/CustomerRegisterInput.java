package com.api.customer_service.domain.input;

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
public class CustomerRegisterInput {

    private String cpf;
    private String email;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private String password;
    private String xClientId;
    private UUID xRequestId;
    private String token;
}
