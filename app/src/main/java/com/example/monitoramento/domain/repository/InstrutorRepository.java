package com.example.monitoramento.domain.repository;

import com.example.monitoramento.domain.entities.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InstrutorRepository extends JpaRepository<Instrutor, UUID> {

    Optional<Instrutor> findInstrutorByCpf(String id);
}
