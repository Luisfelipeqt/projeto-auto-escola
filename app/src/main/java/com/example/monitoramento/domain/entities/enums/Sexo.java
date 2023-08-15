package com.example.monitoramento.domain.entities.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Sexo {
    MASCULINO("MASCULINO"),
    FEMININO("FEMININO"),
    NAO_BINARIO("NAO_BINARIO"),
    OUTRO("OUTRO");

    private final String nome;

    Sexo(String nome) {
        this.nome = nome;
    }


}
