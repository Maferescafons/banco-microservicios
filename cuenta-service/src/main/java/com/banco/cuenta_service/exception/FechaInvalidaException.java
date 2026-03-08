package com.banco.cuenta_service.exception;

public class FechaInvalidaException extends RuntimeException {

    public FechaInvalidaException(String mensaje){
        super(mensaje);
    }

}