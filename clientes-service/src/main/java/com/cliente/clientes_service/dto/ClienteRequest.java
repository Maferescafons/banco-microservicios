package com.cliente.clientes_service.dto;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ClienteRequest {

    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
    private Boolean estado;

    // datos de la cuenta
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;
}
