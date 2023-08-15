package com.example.monitoramento.api.service.cfc;

import com.example.monitoramento.api.dtos.CFCRequest;
import com.example.monitoramento.api.dtos.CFCResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICFCService {

    List<CFCResponse> buscarListaDeCFCs();
    Optional<CFCResponse> buscarCFC(UUID id);
    void criarCFC(CFCRequest cfcRequest);
    void atualizarCFC(UUID cnpj, CFCRequest cfcRequest);
    void deletarCFC(UUID cnpj);
}
