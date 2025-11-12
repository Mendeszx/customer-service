package com.api.customer_service.infra.database.repository;

import com.api.customer_service.infra.database.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InformationRepository extends JpaRepository<InformationEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}