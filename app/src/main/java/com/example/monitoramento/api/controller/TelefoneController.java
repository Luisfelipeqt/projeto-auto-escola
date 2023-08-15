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

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/telefone/v1")
public class TelefoneController {


    private final ITelefoneService telefoneService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TelefoneResponse>> buscarListaDeTelefones() {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarListaDeTelefones());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<TelefoneResponse>> buscarTelefone(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarTelefone(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = APPLICATION_JSON_VALUE,
                consumes = APPLICATION_JSON_VALUE)
    public void criarTelefone(@RequestBody @Valid TelefoneRequest telefoneRequest) {
        telefoneService.criarTelefone(telefoneRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void atualizarTelefone(@PathVariable UUID id, @RequestBody @Valid TelefoneRequest telefoneRequest) {
        telefoneService.atualizarTelefone(id, telefoneRequest);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public void deletarTelefone(@PathVariable UUID id) {
        telefoneService.deletarTelefone(id);
    }
}
