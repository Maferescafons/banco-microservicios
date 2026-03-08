package com.banco.cuenta_service.controller;

import org.springframework.web.bind.annotation.*;

import com.banco.cuenta_service.dto.MovimientoRequest;
import com.banco.cuenta_service.entity.Movimiento;
import com.banco.cuenta_service.service.MovimientoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service){
        this.service = service;
    }

    @PostMapping
    public Movimiento registrar(@Valid @RequestBody MovimientoRequest request){
        return service.registrarMovimiento(request);
    }
}