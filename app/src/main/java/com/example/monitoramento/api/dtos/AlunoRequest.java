package com.example.monitoramento.api.dtos;

import com.example.monitoramento.domain.entities.Aluno;
import com.example.monitoramento.domain.entities.Endereco;
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

import static com.fasterxml.jackson.annotation.JsonFormat.*;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import static org.springframework.format.annotation.DateTimeFormat.*;

@JsonNaming(SnakeCaseStrategy.class)
public record AlunoRequest(
        @CPF
        String cpf,
        @NotNull
        String primeiroNome,
        @NotNull
        String sobreNome,
        @NotNull
        Sexo sexo,
        @NotNull
        Set<String> email,
        @NotNull
        Set<Telefone> telefone,
        @NotNull
        @DateTimeFormat(iso = ISO.DATE)
        @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        Endereco endereco
) {

    public static Aluno toEntidade(AlunoRequest alunoRequest){
        if(alunoRequest == null){
            return null;
        }

        return new Aluno(
                alunoRequest.cpf(),
                alunoRequest.primeiroNome(),
                alunoRequest.sobreNome(),
                alunoRequest.sexo(),
                alunoRequest.email(),
                alunoRequest.telefone(),
                alunoRequest.dataNascimento(),
                alunoRequest.endereco()
        );
    }
}
