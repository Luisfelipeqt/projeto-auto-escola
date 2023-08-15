package com.example.monitoramento.domain.exceptions;

public class TelefoneNotFoundException extends ModelNotFoundException {

    public TelefoneNotFoundException() {
        super("Telefone não econtrado");
    }

    public TelefoneNotFoundException(String message) {
        super(message);
    }

}

