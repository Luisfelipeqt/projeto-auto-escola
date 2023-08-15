package com.example.monitoramento.api.dtos;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import com.example.monitoramento.domain.entities.Telefone;
import com.example.monitoramento.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(SnakeCaseStrategy.class)
public record TelefoneResponse(

        UUID id,
        @Enumerated(EnumType.STRING)
        TelefoneTipo tipo,
        String numero,
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

    public static TelefoneResponse toResponse(Telefone telefone) {
        if (telefone == null) {
            return null;
        }
        return new TelefoneResponse(
                telefone.getId(),
                telefone.getTipo(),
                telefone.getNumero(),
                telefone.getCreatedBy(),
                telefone.getLastModifiedBy(),
                telefone.getCreatedAt(),
                telefone.getUpdatedAt());
    }
}
