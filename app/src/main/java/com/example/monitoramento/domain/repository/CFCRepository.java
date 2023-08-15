package com.example.monitoramento.domain.repository;


import com.example.monitoramento.domain.entities.Cfc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CFCRepository extends JpaRepository<Cfc, UUID> {
    Optional<Cfc> findCfcByCnpj(String id);
}
