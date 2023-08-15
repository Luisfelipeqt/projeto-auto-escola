package com.example.monitoramento.api.controller;

import com.example.monitoramento.api.dtos.InstrutorRequest;
import com.example.monitoramento.api.dtos.InstrutorResponse;
import com.example.monitoramento.api.service.instrutor.IInstrutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instrutor/v1")
public class InstrutorController {


    private final IInstrutorService instrutorService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InstrutorResponse>> buscarListaDeInstrutors() {
        return ResponseEntity.status(HttpStatus.OK).body(instrutorService.buscarListaDeInstrutors());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<InstrutorResponse> buscarInstrutor(@PathVariable UUID id) {
        return instrutorService.buscarInstrutor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE,
                produces = APPLICATION_JSON_VALUE)
    public void criarInstrutor(@RequestBody InstrutorRequest instrutorRequest) {
        instrutorService.criarInstrutor(instrutorRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}",
                consumes = APPLICATION_JSON_VALUE,
                produces = APPLICATION_JSON_VALUE)
    public void atualizarInstrutor(@PathVariable UUID id, @RequestBody InstrutorRequest instrutorRequest) {
        instrutorService.atualizarInstrutor(id, instrutorRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void deletarInstrutor(@PathVariable UUID id) {
        instrutorService.deletarInstrutor(id);
    }
}
