package com.cliente.clientes_service.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cliente.clientes_service.dto.ClienteRequest;
import com.cliente.clientes_service.entity.Cliente;
import com.cliente.clientes_service.event.ClienteCreadoEvent;
import com.cliente.clientes_service.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public ClienteService(ClienteRepository repository,
                          RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Cliente> listar(){
        return repository.findAll();
    }

        public Cliente crear(ClienteRequest request){

        Cliente cliente = new Cliente();

        cliente.setNombre(request.getNombre());
        cliente.setGenero(request.getGenero());
        cliente.setEdad(request.getEdad());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());
        cliente.setPassword(request.getPassword());
        cliente.setEstado(request.getEstado());

        Cliente clienteGuardado = repository.save(cliente);

        ClienteCreadoEvent event = new ClienteCreadoEvent();
        event.setClienteId(clienteGuardado.getId());
        event.setNombre(clienteGuardado.getNombre());

        // datos de cuenta
        event.setNumeroCuenta(request.getNumeroCuenta());
        event.setTipoCuenta(request.getTipoCuenta());
        event.setSaldo(request.getSaldo());

        rabbitTemplate.convertAndSend(
                "cliente.exchange",
                "cliente.creado",
                event
        );

        return clienteGuardado;
    }

    public Cliente obtenerCliente(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente actualizar(Long id, Cliente cliente){
        cliente.setId(id);
        return repository.save(cliente);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}