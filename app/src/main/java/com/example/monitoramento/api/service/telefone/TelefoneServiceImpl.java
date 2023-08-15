package com.example.monitoramento.api.service.telefone;

import com.example.monitoramento.api.dtos.TelefoneRequest;
import com.example.monitoramento.api.dtos.TelefoneResponse;
import com.example.monitoramento.domain.exceptions.TelefoneNotFoundException;
import com.example.monitoramento.domain.exceptions.ModelNotFoundException;
import com.example.monitoramento.domain.repository.TelefoneRepository;
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
public class TelefoneServiceImpl implements ITelefoneService {

    private final TelefoneRepository telefoneRepository;


    @Override
    public List<TelefoneResponse> buscarListaDeTelefones() {
        try {
            var buscarTodosTelefones = telefoneRepository.findAll();
            log.info("Todos os Telefones foram encontrados!");
            return buscarTodosTelefones
                    .stream()
                    .map(TelefoneResponse::toResponse)
                    .toList();
        } catch (RuntimeException e) {
            log.info("Aconteceu o seguinte erro {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<TelefoneResponse> buscarTelefone(UUID id) {
        var buscarTelefone = telefoneRepository.findById(id);
        if (buscarTelefone.isEmpty()) {
            log.warn("Telefone com id: {} não encontrado!", id);
            throw new ModelNotFoundException("ID não encontrado!");
        }
        log.info("Telefone com id: {} foi encontrado!", id);
        return buscarTelefone.map(TelefoneResponse::toResponse);
    }

    @Override
    public void criarTelefone(TelefoneRequest telefoneRequest) {
        var buscarTelefone = telefoneRepository.findTelefoneByNumero(telefoneRequest.numero());
        if (buscarTelefone.isPresent()) {
            log.warn("Telefone com número: {} já está cadastrado!", telefoneRequest.numero());
            throw new TelefoneNotFoundException("Telefone já cadastrado!");
        }
        telefoneRepository.save(TelefoneRequest.toEntidade(telefoneRequest));
        log.info("Telefone: {} cadastrado com sucesso!", telefoneRequest.numero());
    }

    @Override
    public void atualizarTelefone(UUID id, TelefoneRequest telefoneRequest) {
        var buscarTelefone = telefoneRepository.findById(id);
        if (buscarTelefone.isEmpty()) {
            log.warn("Telefone com o id: {} não encontrado!", id);
            throw new TelefoneNotFoundException("Telefone não encontrado!");
        }
        buscarTelefone.map(entidade -> {
            entidade.setTipo(telefoneRequest.tipo());
            entidade.setNumero(telefoneRequest.numero());
            return telefoneRepository.save(entidade);
        });
        log.info("Telefone: {} atualizado com sucesso!",telefoneRequest.numero());
    }

        @Override
        public void deletarTelefone (UUID id){
            try {
                var buscarTelefone = telefoneRepository.findById(id).orElseThrow(() -> new TelefoneNotFoundException("Telefone não encontrado!"));
                telefoneRepository.delete(buscarTelefone);
                log.info("Telefone com o id: {} encontrado e deletado!", id);
            }
            catch(RuntimeException e) {
                log.warn("Aconteceu o seguinte error: {}", e.getMessage());
            }
        }
    }
