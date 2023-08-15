package com.example.monitoramento.domain.entities;

import com.example.monitoramento.domain.entities.enums.Estado;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class Endereco {
    @Column(nullable = false, unique = false, length = 100)
    private String logradouro;
    @Column(nullable = false, unique = false, length = 10)
    private String numero;
    @Column(nullable = false, unique = false, length = 100 )
    private String complemento;
    @Column(nullable = false, unique = false, length = 100 )
    private String bairro;
    @Column(nullable = false, unique = false, length = 100)
    private String cidade;
    @Column(nullable = false, unique = false, length = 20)
    private Estado estado = Estado.MA;
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = false, length = 10)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O campo 'cep' deve ter o formato '12345-678'")
    private String cep;
}