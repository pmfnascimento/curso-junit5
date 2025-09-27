package com.web.java.barriga.dominio.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String mensagem) {
        super(mensagem);
    }
}
