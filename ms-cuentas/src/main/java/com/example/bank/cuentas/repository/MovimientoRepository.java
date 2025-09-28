package com.example.bank.cuentas.repository;

import com.example.bank.cuentas.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta_NumeroCuenta(String numeroCuenta);
    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(String numeroCuenta, LocalDateTime inicio, LocalDateTime fin);
}
