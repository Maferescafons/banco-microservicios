package com.banco.cuenta_service.exception;

public class SaldoNoDisponibleException extends RuntimeException {

    public SaldoNoDisponibleException() {
        super("Saldo no disponible");
    }

}
