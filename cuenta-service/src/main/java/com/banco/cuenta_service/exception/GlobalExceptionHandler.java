package com.banco.cuenta_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<String> manejarSaldo(SaldoNoDisponibleException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<String> manejarCuenta(CuentaNoEncontradaException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

     @ExceptionHandler(FechaInvalidaException.class)
    public ResponseEntity<String> manejarFecha(FechaInvalidaException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(CuentaInactivaException.class)
    public ResponseEntity<String> manejarCuentaInactiva(CuentaInactivaException ex){
        return ResponseEntity
            .badRequest()
            .body(ex.getMessage());
    }

}
