package com.example.monitoramento.domain.exceptions;

public class InstrutorNotFoundException extends ModelNotFoundException {

    public InstrutorNotFoundException() {
        super("Instrutor não econtrado");
    }

    public InstrutorNotFoundException(String message) {
        super(message);
    }

}

