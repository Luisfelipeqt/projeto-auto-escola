package com.example.monitoramento.domain.repository;

import com.example.monitoramento.domain.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {
    Optional<Telefone> findTelefoneByNumero(String id);

}
