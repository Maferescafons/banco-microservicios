package com.banco.cuenta_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movimiento {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate fecha;
private String tipoMovimiento;
private BigDecimal valor;
private BigDecimal saldo;
private Long cuentaId;
}
