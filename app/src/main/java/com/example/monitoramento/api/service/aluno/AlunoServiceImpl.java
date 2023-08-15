package com.example.monitoramento.api.service.aluno;

import com.example.monitoramento.api.dtos.AlunoResponse;
import com.example.monitoramento.domain.exceptions.AlunoNotFoundException;
import com.example.monitoramento.domain.exceptions.ModelNotFoundException;
import com.example.monitoramento.api.dtos.AlunoRequest;
import com.example.monitoramento.domain.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.monitoramento.api.dtos.AlunoRequest.toEntidade;

@Log4j2
@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements IAlunoService {


    private final AlunoRepository alunoRepository;

    @Override
    public List<AlunoResponse> buscarListaDeAlunos() {
        try {
            var buscarAlunos = alunoRepository.findAll();
            log.info("Todos os alunos foram encontrados!");
            return buscarAlunos
                    .stream()
                    .map(AlunoResponse::toResponse)
                    .toList();
        } catch (RuntimeException e) {
            log.info("Aconteceu o seguinte erro {}", e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<AlunoResponse> buscarAluno(UUID id) {
        var buscarAluno = alunoRepository.findById(id);
        if (buscarAluno.isEmpty()) {
            log.warn("Aluno com o ID: {} não encontrado!", id);
            throw new ModelNotFoundException("ID não encontrado!");
        }
        return buscarAluno.map(AlunoResponse::toResponse);
    }

    @Override
    public void criarAluno(AlunoRequest alunoRequest) {
        var buscarAlunoPorCpf = alunoRepository.findAlunoByCpf(alunoRequest.cpf());
        if(buscarAlunoPorCpf.isPresent()){
            log.warn("Aluno com o CPF: {} já cadastrado!", alunoRequest.cpf());
            throw new AlunoNotFoundException("Aluno já cadastrado!");
        }
        alunoRepository.save(toEntidade(alunoRequest));

    }

    @Override
    public void atualizarAluno(UUID id, AlunoRequest alunoRequest) {
        var buscarAlunoPorCpf = alunoRepository.findById(id);
        if(buscarAlunoPorCpf.isEmpty()){
            log.warn("Aluno com o ID: {} não encontrado!", id);
            throw new AlunoNotFoundException("ID não encontrado!");
        }
        buscarAlunoPorCpf.map(entidade -> {
            entidade.setCpf(alunoRequest.cpf());
            entidade.setPrimeiroNome(alunoRequest.primeiroNome());
            entidade.setSobreNome(alunoRequest.sobreNome());
            entidade.setSexo(alunoRequest.sexo());
            entidade.setEmails(alunoRequest.email());
            entidade.setTelefones(alunoRequest.telefone());
            entidade.setDataNascimento(alunoRequest.dataNascimento());
            entidade.setEndereco(alunoRequest.endereco());
            return alunoRepository.save(entidade);
        });
        log.info("Aluno com ID: {} atualizado com sucesso!", id);
    }

    @Override
    public void deletarAluno(UUID id) {
        try {
            var buscarAluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado!"));
            alunoRepository.delete(buscarAluno);
            log.info("Aluno com o CPF: {} encontrado e deletado!", id);
        }
        catch(RuntimeException e){
            log.warn("Aconteceu o seguinte error: {}", e.getMessage());
        }
    }
}
