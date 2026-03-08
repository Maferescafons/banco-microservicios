package com.banco.cuenta_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banco.cuenta_service.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
    @Query("""
        SELECT m
        FROM Movimiento m
        JOIN Cuenta c ON m.cuentaId = c.id
        WHERE m.fecha BETWEEN :inicio AND :fin
        """)
    List<Movimiento> buscarMovimientos(LocalDate inicio, LocalDate fin);

}