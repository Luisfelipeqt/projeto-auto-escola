package com.example.monitoramento.api.dtos;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import com.example.monitoramento.domain.entities.Cfc;
import com.example.monitoramento.domain.entities.Endereco;
import com.example.monitoramento.domain.entities.Instrutor;
import com.example.monitoramento.domain.entities.Telefone;
import com.example.monitoramento.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@JsonNaming(SnakeCaseStrategy.class)
public record InstrutorRequest(
        @CPF
        @NotNull
        @NotBlank
        String cpf,
        @NotNull
        @NotBlank
        String primeiroNome,
        @NotNull
        @NotBlank
        String sobreNome,
        @NotNull
        Sexo sexo,
        Set<String> emails,
        Set<Telefone> telefones,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        Set<Cfc> cfcs,
        Endereco endereco
) {

    public static Instrutor toEntidade(InstrutorRequest instrutorRequest) {
        if (instrutorRequest == null) {
            return null;
        }
        return new Instrutor(
                instrutorRequest.cpf,
                instrutorRequest.primeiroNome,
                instrutorRequest.sobreNome,
                instrutorRequest.sexo,
                instrutorRequest.emails,
                instrutorRequest.telefones,
                instrutorRequest.dataNascimento,
                instrutorRequest.cfcs,
                instrutorRequest.endereco
        );
    }


}
