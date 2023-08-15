package com.example.monitoramento.api.service.telefone;

import com.example.monitoramento.api.dtos.TelefoneRequest;
import com.example.monitoramento.api.dtos.TelefoneResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITelefoneService {

    List<TelefoneResponse> buscarListaDeTelefones();
    Optional<TelefoneResponse> buscarTelefone(UUID id);
    void criarTelefone(TelefoneRequest telefoneRequest);
    void atualizarTelefone(UUID id, TelefoneRequest telefoneRequest);
    void deletarTelefone(UUID id);
}
