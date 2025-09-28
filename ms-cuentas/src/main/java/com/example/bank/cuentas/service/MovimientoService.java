package com.example.bank.cuentas.service;

import com.example.bank.cuentas.model.Cuenta;
import com.example.bank.cuentas.model.Movimiento;
import com.example.bank.cuentas.repository.CuentaRepository;
import com.example.bank.cuentas.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public Movimiento create(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(movimiento.getValor());

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldo(nuevoSaldo);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> findByNumeroCuenta(String numeroCuenta) {
        return movimientoRepository.findByCuenta_NumeroCuenta(numeroCuenta);
    }

    public Optional<Movimiento> findById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Transactional
    public Optional<Movimiento> update(Long id, Movimiento updated) {
        return movimientoRepository.findById(id).map(mov -> {
            mov.setTipoMovimiento(updated.getTipoMovimiento());
            mov.setValor(updated.getValor());
            return movimientoRepository.save(mov);
        });
    }

    @Transactional
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }

    public List<Movimiento> findByCuentaAndFechaBetween(String numeroCuenta, java.time.LocalDate inicio, java.time.LocalDate fin) {
        return movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(
                numeroCuenta,
                inicio.atStartOfDay(),
                fin.atTime(23, 59, 59)
        );
    }
}
