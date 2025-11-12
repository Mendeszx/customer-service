package com.api.customer_service.infra.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_account")
public class AccountEntity extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", updatable = false, nullable = false)
    private UUID accountId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "account_type", length = 100, nullable = false)
    private String accountType;
}
