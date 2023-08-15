package com.example.monitoramento.api.controller;

import com.example.monitoramento.api.dtos.TelefoneRequest;
import com.example.monitoramento.api.dtos.TelefoneResponse;
import com.example.monitoramento.api.service.telefone.ITelefoneService;
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
@RequestMapping("/api/telefone/v1")
public class TelefoneController {


    private final ITelefoneService telefoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TelefoneResponse>> buscarListaDeTelefones() {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarListaDeTelefones());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<TelefoneResponse>> buscarTelefone(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarTelefone(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTelefone(@RequestBody @Valid TelefoneRequest telefoneRequest) {
        telefoneService.criarTelefone(telefoneRequest);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
                                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarTelefone(@PathVariable UUID id, @RequestBody @Valid TelefoneRequest telefoneRequest) {
        telefoneService.atualizarTelefone(id, telefoneRequest);

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTelefone(@PathVariable UUID id) {
        telefoneService.deletarTelefone(id);
    }
}
