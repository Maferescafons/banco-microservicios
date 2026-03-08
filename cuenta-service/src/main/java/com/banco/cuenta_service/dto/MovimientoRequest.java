package com.banco.cuenta_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimientoRequest {

    @NotNull(message = "La cuenta es obligatoria")
    private Long cuentaId;

    @NotNull(message = "El valor del movimiento es obligatorio")
    @DecimalMin(value = "-1000000000.00", inclusive = true, message = "Valor inválido")
    private BigDecimal valor;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;
}