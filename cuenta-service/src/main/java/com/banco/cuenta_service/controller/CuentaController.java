package com.banco.cuenta_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.banco.cuenta_service.entity.Cuenta;
import com.banco.cuenta_service.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cuenta> listar(){
        return service.listar();
    }

    @PostMapping
    public Cuenta crear(@RequestBody Cuenta cuenta){
        return service.crear(cuenta);
    }

    @PutMapping("/{id}")
    public Cuenta actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta){
        return service.actualizar(id, cuenta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}