package com.banco.cuenta_service.service;

import org.springframework.stereotype.Service;

import com.banco.cuenta_service.entity.Cuenta;
import com.banco.cuenta_service.repository.CuentaRepository;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public List<Cuenta> listar(){
        return repository.findAll();
    }

    public Cuenta crear(Cuenta cuenta){
        return repository.save(cuenta);
    }

    public Cuenta actualizar(Long id, Cuenta cuenta){
        cuenta.setId(id);
        return repository.save(cuenta);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}
