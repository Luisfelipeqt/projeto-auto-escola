package com.example.monitoramento.api.dtos;

import com.example.monitoramento.domain.entities.Cfc;
import com.example.monitoramento.domain.entities.Endereco;
import com.example.monitoramento.domain.entities.Instrutor;
import com.example.monitoramento.domain.entities.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Set;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record CFCRequest(

        @CNPJ
        @NotNull
        @NotBlank
        String cnpj,
        @NotNull
        @NotBlank
        String nomeFantasia,
        Set<String> emails,
        Set<Telefone> telefones,
        Set<Instrutor> instrutor,
        Endereco endereco
) {

    public static Cfc toEntidade(CFCRequest cfcRequest) {
        if (cfcRequest == null) {
            return null;
        }
        return new Cfc(
                cfcRequest.cnpj(),
                cfcRequest.nomeFantasia(),
                cfcRequest.emails(),
                cfcRequest.telefones(),
                cfcRequest.endereco(),
                cfcRequest.instrutor()
        );

    }
}
