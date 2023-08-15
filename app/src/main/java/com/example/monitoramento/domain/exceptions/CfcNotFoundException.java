package com.example.monitoramento.domain.exceptions;

public class CfcNotFoundException extends ModelNotFoundException {

    public CfcNotFoundException() {
        super("CFC não econtrado");
    }

    public CfcNotFoundException(String message) {
        super(message);
    }

}

