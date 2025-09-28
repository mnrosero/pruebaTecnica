package com.example.bank.clientes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {
    @Column(nullable = false, unique = true)
    private String clienteId;

    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private boolean estado = true;
}
