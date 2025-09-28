package com.example.bank.cuentas.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CuentaDTO {
    @NotBlank
    private String numeroCuenta;

    @NotBlank
    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private boolean estado = true;

    @NotBlank
    private String clienteId;
}
