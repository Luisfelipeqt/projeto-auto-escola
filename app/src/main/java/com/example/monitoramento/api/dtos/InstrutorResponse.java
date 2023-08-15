package com.example.monitoramento.api.dtos;

import com.example.monitoramento.domain.entities.Cfc;
import com.example.monitoramento.domain.entities.Endereco;
import com.example.monitoramento.domain.entities.Instrutor;
import com.example.monitoramento.domain.entities.Telefone;
import com.example.monitoramento.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

@JsonNaming(SnakeCaseStrategy.class)
public record InstrutorResponse(
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
        Set<Cfc> Cfcs,
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

    public static InstrutorResponse toResponse(Instrutor instrutor) {
        if (instrutor == null) {
            return null;
        }
        return new InstrutorResponse(
                instrutor.getId(),
                instrutor.getCpf(),
                instrutor.getPrimeiroNome(),
                instrutor.getSobreNome(),
                instrutor.getSexo(),
                instrutor.getEmails(),
                instrutor.getTelefones(),
                instrutor.getDataNascimento(),
                instrutor.getCfcs(),
                instrutor.getEndereco(),
                instrutor.getCreatedBy(),
                instrutor.getLastModifiedBy(),
                instrutor.getCreatedAt(),
                instrutor.getUpdatedAt()
        );
    }
}
