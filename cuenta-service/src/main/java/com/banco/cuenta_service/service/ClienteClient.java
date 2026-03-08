package com.banco.cuenta_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banco.cuenta_service.dto.ClienteDTO;

@Service
public class ClienteClient {

    private final RestTemplate restTemplate;

    public ClienteClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ClienteDTO obtenerCliente(Long id){

        try{
           // String url = "http://localhost:8080/clientes/" + id;
            String url = "http://cliente-service:8080/clientes/" + id;
            return restTemplate.getForObject(url, ClienteDTO.class);

        }catch(Exception e){

            ClienteDTO cliente = new ClienteDTO();
            cliente.setNombre("Cliente no disponible");

            return cliente;
        }
    }
}
