package com.banco.cuenta_service.exception;

public class CuentaInactivaException extends RuntimeException {

    public CuentaInactivaException(){
        super("La cuenta está inactiva");
    }

}