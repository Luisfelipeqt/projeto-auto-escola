package com.example.monitoramento.api.service.aluno;

import com.example.monitoramento.api.dtos.AlunoResponse;
import com.example.monitoramento.api.dtos.AlunoRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAlunoService {

    List<AlunoResponse> buscarListaDeAlunos();
    Optional<AlunoResponse> buscarAluno(UUID id);
    void criarAluno(AlunoRequest alunoRequest);
    void atualizarAluno(UUID id, AlunoRequest alunoRequest);
    void deletarAluno(UUID id);
}
