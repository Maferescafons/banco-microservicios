package com.cliente.clientes_service.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clientes")
public class Cliente extends Persona {
    private String password;

    private Boolean estado;
}
