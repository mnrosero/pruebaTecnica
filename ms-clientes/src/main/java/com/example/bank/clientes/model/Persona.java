package com.example.bank.clientes.model;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String genero;
    private Integer edad;

    @Column(unique = true)
    private String identificacion;

    private String direccion;
    private String telefono;
}
