package com.example.monitoramento.domain.repository;

import com.example.monitoramento.domain.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findAlunoByCpf(String id);
}
