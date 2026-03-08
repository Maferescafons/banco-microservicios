package com.banco.cuenta_service.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.banco.cuenta_service.dto.MovimientoRequest;
import com.banco.cuenta_service.entity.Cuenta;
import com.banco.cuenta_service.entity.Movimiento;
import com.banco.cuenta_service.exception.CuentaInactivaException;
import com.banco.cuenta_service.exception.CuentaNoEncontradaException;
import com.banco.cuenta_service.exception.SaldoNoDisponibleException;
import com.banco.cuenta_service.repository.CuentaRepository;
import com.banco.cuenta_service.repository.MovimientoRepository;
 import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public MovimientoService(CuentaRepository cuentaRepository,
                             MovimientoRepository movimientoRepository){

        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }
    
    @Transactional
    public Movimiento registrarMovimiento(MovimientoRequest request){

        Cuenta cuenta = cuentaRepository
                .obtenerCuentaParaActualizar(request.getCuentaId());

        if(cuenta == null){
            throw new CuentaNoEncontradaException(request.getCuentaId());
        }

        // VALIDAR CUENTA ACTIVA
        if(!cuenta.getEstado()){
            throw new CuentaInactivaException();
        }

        BigDecimal nuevoSaldo = cuenta.getSaldo().add(request.getValor());

        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new SaldoNoDisponibleException();
        }

        cuenta.setSaldo(nuevoSaldo);

        Movimiento movimiento = new Movimiento();

        movimiento.setFecha(LocalDate.now());
        movimiento.setCuentaId(cuenta.getId());
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setValor(request.getValor());
        movimiento.setSaldo(nuevoSaldo);

        cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

}