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
@Table(name = "tb_consent")
public class ConsentEntity extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "consent_id", updatable = false, nullable = false)
    private UUID consentId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "version", length = 10)
    private String version;

    @Column(name = "accept", nullable = false)
    private boolean accept;
}
