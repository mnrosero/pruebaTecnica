package com.example.bank.clientes.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String clienteId;
    private String contrasenia;
    private boolean estado;
}
