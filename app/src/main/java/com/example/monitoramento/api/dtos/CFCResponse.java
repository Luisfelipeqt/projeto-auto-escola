package com.example.monitoramento.api.dtos;

import com.example.monitoramento.domain.entities.Cfc;
import com.example.monitoramento.domain.entities.Endereco;
import com.example.monitoramento.domain.entities.Instrutor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

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

@JsonNaming(SnakeCaseStrategy.class)
public record CFCResponse(

        UUID id,

        String cnpj,
        String nomeFantasia,
        Set<Instrutor> instrutor,
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

    public static CFCResponse toResponse(Cfc cfc){
        if(cfc == null){
            return null;
        }

        return new CFCResponse(
                cfc.getId(),
                cfc.getCnpj(),
                cfc.getNomeFantasia(),
                cfc.getInstrutores(),
                cfc.getEndereco(),
                cfc.getCreatedBy(),
                cfc.getLastModifiedBy(),
                cfc.getCreatedAt(),
                cfc.getUpdatedAt()
        );
    }
}
