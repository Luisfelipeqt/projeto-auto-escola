package com.example.monitoramento.api.dtos;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

import com.example.monitoramento.domain.entities.Telefone;
import com.example.monitoramento.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.UUID;

@JsonNaming(SnakeCaseStrategy.class)
public record TelefoneRequest(

        @Enumerated(EnumType.STRING)
        TelefoneTipo tipo,
        String numero

) {

    public static Telefone toEntidade(TelefoneRequest telefoneRequest){
        if(telefoneRequest == null){
            return null;
        }
        return new Telefone(
                telefoneRequest.tipo,
                telefoneRequest.numero
        );
    }
}
