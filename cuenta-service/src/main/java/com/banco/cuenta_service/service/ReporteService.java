package com.banco.cuenta_service.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Service;

import com.banco.cuenta_service.dto.ClienteDTO;
import com.banco.cuenta_service.dto.ReporteDTO;
import com.banco.cuenta_service.entity.Cuenta;
import com.banco.cuenta_service.entity.Movimiento;
import com.banco.cuenta_service.exception.FechaInvalidaException;
import com.banco.cuenta_service.repository.CuentaRepository;
import com.banco.cuenta_service.repository.MovimientoRepository;

@Service
public class ReporteService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final ClienteClient clienteClient;

    public ReporteService(MovimientoRepository movimientoRepository,
                          CuentaRepository cuentaRepository,
                          ClienteClient clienteClient){

        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.clienteClient = clienteClient;
    }

    public List<ReporteDTO> generarReporte(LocalDate inicio, LocalDate fin){
        if(inicio.isAfter(fin)){
            throw new FechaInvalidaException(
        "La fecha inicio no puede ser mayor que la fecha fin");
    }

        List<Movimiento> movimientos =
                movimientoRepository.buscarMovimientos(inicio, fin);

        List<ReporteDTO> reporte = new ArrayList<>();

        Map<Long, ClienteDTO> cacheClientes = new HashMap<>();

        for(Movimiento m : movimientos){

            Cuenta cuenta = cuentaRepository
                    .findById(m.getCuentaId())
                    .orElseThrow();

            ClienteDTO cliente;

            if(cacheClientes.containsKey(cuenta.getClienteId())){
                cliente = cacheClientes.get(cuenta.getClienteId());
            }else{
                cliente = clienteClient.obtenerCliente(cuenta.getClienteId());
                cacheClientes.put(cuenta.getClienteId(), cliente);
            }

            ReporteDTO dto = new ReporteDTO();

            dto.setFecha(m.getFecha());
            dto.setCliente(cliente.getNombre());
            dto.setNumeroCuenta(cuenta.getNumeroCuenta());
            dto.setTipoCuenta(cuenta.getTipoCuenta());
            dto.setSaldoInicial(cuenta.getSaldo());
            dto.setEstado(cuenta.getEstado());
            dto.setMovimiento(m.getValor());
            dto.setSaldoDisponible(m.getSaldo());

            reporte.add(dto);
        }

        return reporte;
    }
}