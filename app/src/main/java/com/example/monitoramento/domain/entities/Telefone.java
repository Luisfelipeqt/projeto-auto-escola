package com.example.monitoramento.domain.entities;


import com.example.monitoramento.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_telefone")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(SnakeCaseStrategy.class)
public class Telefone extends Entidade {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TelefoneTipo tipo = TelefoneTipo.CELULAR;
    @Column(nullable = false, unique = true, length = 15)
    private String numero;
}