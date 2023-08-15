package com.example.monitoramento.domain.entities.enums;

import lombok.Getter;

@Getter
public enum TelefoneTipo {
    CELULAR("PESSOAL"),
    FIXO("RESIDENCIAL"),
    TRABALHO("COMERCIAL"),
    OUTRO("OUTRO");


    private final String nome;

    TelefoneTipo(String nome) {
        this.nome = nome;
    }
}