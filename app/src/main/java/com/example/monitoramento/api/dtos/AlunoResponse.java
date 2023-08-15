package com.example.monitoramento.api.dtos;

import com.example.monitoramento.domain.entities.Aluno;
import com.example.monitoramento.domain.entities.Endereco;
import com.example.monitoramento.domain.entities.Telefone;
import com.example.monitoramento.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "cpf", "primeiroNome", "sobreNome",
        "sexo", "dataNascimento", "createdBy", "lastModifiedBy",
        "createdAt", "updatedAt", "emails", "telefones", "endereco"})
public record AlunoResponse(
        UUID id,
        String cpf,
        String primeiroNome,
        String sobreNome,
        Sexo sexo,
        Set<String> emails,
        Set<Telefone> telefones,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        Endereco endereco,

        String createdBy,
        String lastModifiedBy,

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,

        @LastModifiedDate @Column(name = "updated_at")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {

    public static AlunoResponse toResponse(Aluno aluno) {
        if (aluno == null) {
            return null;
        }

        return new AlunoResponse(
                aluno.getId(),
                aluno.getCpf(),
                aluno.getPrimeiroNome(),
                aluno.getSobreNome(),
                aluno.getSexo(),
                aluno.getEmails(),
                aluno.getTelefones(),
                aluno.getDataNascimento(),
                aluno.getEndereco(),
                aluno.getCreatedBy(),
                aluno.getLastModifiedBy(),
                aluno.getCreatedAt(),
                aluno.getUpdatedAt()
        );
    }
}
