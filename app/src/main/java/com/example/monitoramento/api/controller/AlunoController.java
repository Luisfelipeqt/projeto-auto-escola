package com.example.monitoramento.api.controller;

import com.example.monitoramento.api.dtos.AlunoRequest;
import com.example.monitoramento.api.dtos.AlunoResponse;
import com.example.monitoramento.api.service.aluno.IAlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aluno/v1")
public class AlunoController {

    private final IAlunoService alunoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlunoResponse>> buscarListaDeAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarListaDeAlunos());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<AlunoResponse>> buscarAluno(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarAluno(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@RequestBody @Valid AlunoRequest alunoRequest) {
        alunoService.criarAluno(alunoRequest);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarAluno(@PathVariable UUID id, @RequestBody AlunoRequest alunoRequest) {
        alunoService.atualizarAluno(id, alunoRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable UUID id) {
        alunoService.deletarAluno(id);
    }
}
