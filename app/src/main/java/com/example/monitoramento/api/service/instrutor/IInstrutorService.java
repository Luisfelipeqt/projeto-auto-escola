package com.example.monitoramento.api.service.instrutor;

import com.example.monitoramento.api.dtos.InstrutorRequest;
import com.example.monitoramento.api.dtos.InstrutorResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IInstrutorService {

    List<InstrutorResponse> buscarListaDeInstrutors();
    Optional<InstrutorResponse> buscarInstrutor(UUID id);
    void criarInstrutor(InstrutorRequest instrutorRequest);
    void atualizarInstrutor(UUID id, InstrutorRequest instrutorRequest);
    void deletarInstrutor(UUID id);
}
