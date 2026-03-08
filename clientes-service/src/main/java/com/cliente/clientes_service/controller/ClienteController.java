package com.cliente.clientes_service.controller;
import org.springframework.web.bind.annotation.*;

import com.cliente.clientes_service.dto.ClienteRequest;
import com.cliente.clientes_service.entity.Cliente;
import com.cliente.clientes_service.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }
    
    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id){
        return service.obtenerCliente(id);
    }

    @GetMapping
    public List<Cliente> listar(){
        return service.listar();
    }

    @PostMapping
    public Cliente crear(@RequestBody ClienteRequest request){
    return service.crear(request);
}

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return service.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}
