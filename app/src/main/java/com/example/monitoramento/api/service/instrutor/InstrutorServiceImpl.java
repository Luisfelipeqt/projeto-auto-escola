package com.example.monitoramento.api.service.instrutor;

import com.example.monitoramento.api.dtos.InstrutorRequest;
import com.example.monitoramento.api.dtos.InstrutorResponse;
import com.example.monitoramento.domain.exceptions.AlunoNotFoundException;
import com.example.monitoramento.domain.exceptions.InstrutorNotFoundException;
import com.example.monitoramento.domain.exceptions.ModelNotFoundException;
import com.example.monitoramento.domain.repository.InstrutorRepository;
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
public class InstrutorServiceImpl implements IInstrutorService {

    private final InstrutorRepository instrutorRepository;


    @Override
    public List<InstrutorResponse> buscarListaDeInstrutors() {
        try {
            var buscarTodosInstrutores = instrutorRepository.findAll();
            log.info("Todos os instrutores foram encontrados!");
            return buscarTodosInstrutores
                    .stream()
                    .map(InstrutorResponse::toResponse)
                    .toList();
        } catch (RuntimeException e) {
            log.info("Aconteceu o seguinte erro {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<InstrutorResponse> buscarInstrutor(UUID id) {
        var buscarInstrutor = instrutorRepository.findById(id);
        if (buscarInstrutor.isEmpty()) {
            log.warn("Instrutor com ID: {} não encontrado!", id);
            throw new ModelNotFoundException("ID não encontrado!");
        }
        log.info("Instrutor com ID: {} foi encontrado!", id);
        return buscarInstrutor.map(InstrutorResponse::toResponse);
    }

    @Override
    public void criarInstrutor(InstrutorRequest instrutorRequest) {
        var buscarInstrutor = instrutorRepository.findInstrutorByCpf(instrutorRequest.cpf());
        if (buscarInstrutor.isPresent()) {
            log.warn("CPF: {} já está cadastrado!", instrutorRequest.cpf());
            throw new InstrutorNotFoundException("CPF já cadastrado!");
        }
        instrutorRepository.save(InstrutorRequest.toEntidade(instrutorRequest));
        log.info("Instrutor: {} com o CPF: {} cadastrado com sucesso!", instrutorRequest.primeiroNome(), instrutorRequest.cpf());
    }

    @Override
    public void atualizarInstrutor(UUID id, InstrutorRequest instrutorRequest) {
        var buscarInstrutor = instrutorRepository.findById(id);
        if (buscarInstrutor.isEmpty()) {
            log.warn("Instrutor com o CPF: {} e ID: {} não encontrado!", instrutorRequest.cpf(),  id);
            throw new InstrutorNotFoundException("Instrutor não encontrado!");
        }
        buscarInstrutor.map(entidade -> {
            entidade.setCpf(instrutorRequest.cpf());
            entidade.setPrimeiroNome(instrutorRequest.primeiroNome());
            entidade.setSobreNome(instrutorRequest.sobreNome());
            entidade.setSexo(instrutorRequest.sexo());
            entidade.setEmails(instrutorRequest.emails());
            entidade.setTelefones(instrutorRequest.telefones());
            entidade.setDataNascimento(instrutorRequest.dataNascimento());
            entidade.setEndereco(instrutorRequest.endereco());
            return instrutorRepository.save(entidade);
        });
        log.info("Instrutor com ID: {} atualizado com sucesso!", id);
    }

        @Override
        public void deletarInstrutor (UUID id){
            try {
                var buscarInstrutor = instrutorRepository.findById(id).orElseThrow(() -> new InstrutorNotFoundException("Instrutor não encontrado!"));
                instrutorRepository.delete(buscarInstrutor);
                log.info("Instrutor com o ID: {} encontrado e deletado!", id);
            }
            catch(RuntimeException e){
                log.warn("Aconteceu o seguinte error: {}", e.getMessage());
            }
        }
    }
