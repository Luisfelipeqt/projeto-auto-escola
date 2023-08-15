package com.example.monitoramento.api.controller;

import com.example.monitoramento.api.dtos.CFCRequest;
import com.example.monitoramento.api.dtos.CFCResponse;
import com.example.monitoramento.api.service.cfc.ICFCService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cfc/v1")
public class CfcController {

    private final ICFCService cfcService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CFCResponse>> buscarListaDeCFCs() {
        return ResponseEntity.status(HttpStatus.OK).body(cfcService.buscarListaDeCFCs());
    }

    @GetMapping(value = "/{id}",
                produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<CFCResponse>> buscarCFC(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(cfcService.buscarCFC(id));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE,
                consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCFC(@RequestBody @Valid CFCRequest cfcRequest) {
        cfcService.criarCFC(cfcRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}",
                consumes = APPLICATION_JSON_VALUE,
                produces = APPLICATION_JSON_VALUE)
    public void atualizarCFC(@PathVariable UUID id, @RequestBody CFCRequest cfcRequest) {
        cfcService.atualizarCFC(id, cfcRequest);
    }

    @DeleteMapping(value = "/{id}",
                   consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCFC(@PathVariable UUID id) {
        cfcService.deletarCFC(id);
    }
}
