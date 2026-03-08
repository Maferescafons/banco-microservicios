package com.banco.cuenta_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.banco.cuenta_service.entity.Cuenta;
import com.banco.cuenta_service.event.ClienteCreadoEvent;
import com.banco.cuenta_service.repository.CuentaRepository;

@Component
public class ClienteListener {

    private final CuentaRepository cuentaRepository;

    public ClienteListener(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

   @RabbitListener(queues = "cliente.queue")
   public void crearCuenta(ClienteCreadoEvent event){
    Cuenta cuenta = new Cuenta();
        cuenta.setClienteId(event.getClienteId());
        cuenta.setNumeroCuenta(event.getNumeroCuenta());
        cuenta.setTipoCuenta(event.getTipoCuenta());
        cuenta.setSaldo(event.getSaldo());
        cuenta.setEstado(true);
        cuentaRepository.save(cuenta);
        System.out.println("Cuenta creada para cliente " + event.getClienteId());
    }
}