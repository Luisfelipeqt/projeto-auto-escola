package com.example.monitoramento.domain.exceptions;

public class AlunoNotFoundException extends ModelNotFoundException {

    public AlunoNotFoundException() {
        super("Aluno não econtrado");
    }

    public AlunoNotFoundException(String message) {
        super(message);
    }

}

