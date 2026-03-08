package com.cliente.clientes_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cliente.clientes_service.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}