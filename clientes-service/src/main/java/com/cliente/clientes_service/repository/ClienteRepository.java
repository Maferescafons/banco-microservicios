package com.cliente.clientes_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.clientes_service.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
