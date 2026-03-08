package com.banco.cuenta_service.exception;

public class CuentaNoEncontradaException extends RuntimeException {

    public CuentaNoEncontradaException(Long id) {
        super("Cuenta no encontrada con id: " + id);
    }

}
