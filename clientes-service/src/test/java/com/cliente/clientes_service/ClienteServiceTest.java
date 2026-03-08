package com.cliente.clientes_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.cliente.clientes_service.dto.ClienteRequest;
import com.cliente.clientes_service.entity.Cliente;
import com.cliente.clientes_service.repository.ClienteRepository;
import com.cliente.clientes_service.services.ClienteService;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteService = new ClienteService(
                clienteRepository,
                rabbitTemplate
        );
    }

    @Test
    void crearClienteTest(){

        ClienteRequest request = new ClienteRequest();
        request.setNombre("Juan");
        request.setGenero("M");
        request.setEdad(30);
        request.setIdentificacion("123");
        request.setDireccion("Quito");
        request.setTelefono("099999");
        request.setPassword("1234");
        request.setEstado(true);

        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setId(1L);
        clienteGuardado.setNombre("Juan");

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(clienteGuardado);

        Cliente resultado = clienteService.crear(request);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
}