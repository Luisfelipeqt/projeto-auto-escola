package com.example.monitoramento.api.service.cfc;

import com.example.monitoramento.api.dtos.CFCRequest;
import com.example.monitoramento.api.dtos.CFCResponse;
import com.example.monitoramento.domain.exceptions.AlunoNotFoundException;
import com.example.monitoramento.domain.exceptions.CfcNotFoundException;
import com.example.monitoramento.domain.exceptions.ModelNotFoundException;
import com.example.monitoramento.domain.repository.CFCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CFCServiceImpl implements ICFCService {

    private final CFCRepository cfcRepository;


    @Override
    public List<CFCResponse> buscarListaDeCFCs() {
        try {
            var buscarTodosOsCfcs = cfcRepository.findAll();
            log.info("Todos os alunos foram encontrados!");
            return buscarTodosOsCfcs
                    .stream()
                    .map(CFCResponse::toResponse)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            log.info("Aconteceu o seguinte erro {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<CFCResponse> buscarCFC(UUID id) {
        var buscarCFC = cfcRepository.findById(id);
        if (buscarCFC.isEmpty()) {
            log.warn("CFC com o ID: {} não encontrado!", id);
            throw new ModelNotFoundException("ID não encontrado!");
        }
        return buscarCFC.map(CFCResponse::toResponse);
    }

    @Override
    public void criarCFC(CFCRequest cfcRequest) {
        var buscarCFC = cfcRepository.findCfcByCnpj(cfcRequest.cnpj());
        if(buscarCFC.isPresent()){
            log.warn("CNPJ: {} já está cadastrado!", cfcRequest.cnpj());
            throw new CfcNotFoundException("CNPJ já cadastrado!");
        }
        cfcRepository.save(CFCRequest.toEntidade(cfcRequest));
        log.info("CFC com o CNPJ: {} cadastrado com sucesso!", cfcRequest.cnpj());
    }

    @Override
    public void atualizarCFC(UUID id, CFCRequest cfcRequest) {
        var buscarCFC = cfcRepository.findById(id);
        if(buscarCFC.isEmpty()){
            log.warn("CFC com ID: {} não existe!", id);
            throw new CfcNotFoundException("ID inexistente!");
        }
        buscarCFC.map(entidade -> {
            entidade.setCnpj(cfcRequest.cnpj());
            entidade.setNomeFantasia(cfcRequest.nomeFantasia());
            entidade.setInstrutores(cfcRequest.instrutor());
            entidade.setEndereco(cfcRequest.endereco());
            return cfcRepository.save(entidade);
        });
        log.info("CFC {} e ID {} atualizado com sucesso!", cfcRequest.nomeFantasia(), id);
    }

    @Override
    public void deletarCFC(UUID id) {
        try {
            var buscarCfc = cfcRepository.findById(id).orElseThrow(() -> new CfcNotFoundException("CFC não encontrado!"));
            cfcRepository.delete(buscarCfc);
            log.info("CFC com o ID: {} encontrado e deletado!", id);
        }
        catch(RuntimeException e) {
            log.warn("Aconteceu o seguinte erro: {}", e.getMessage());
        }

    }
}
