package com.example.bank.cuentas.service;

import com.example.bank.cuentas.model.Cuenta;
import com.example.bank.cuentas.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository repo;

    public CuentaService(CuentaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Cuenta create(Cuenta c) {
        if (c.getSaldoInicial() == null) {
            c.setSaldoInicial(BigDecimal.ZERO);
        }
        return repo.save(c);
    }

    public Optional<Cuenta> findByNumeroCuenta(String numero) {
        return repo.findByNumeroCuenta(numero);
    }

    public List<Cuenta> findAll() {
        return repo.findAll();
    }

    @Transactional
    public Optional<Cuenta> update(String numeroCuenta, Cuenta updated) {
        return repo.findByNumeroCuenta(numeroCuenta).map(cuenta -> {
            cuenta.setTipoCuenta(updated.getTipoCuenta());
            cuenta.setSaldoInicial(updated.getSaldoInicial());
            cuenta.setEstado(updated.isEstado()); // usa isEstado()
            return repo.save(cuenta);
        });
    }

    @Transactional
    public void delete(String numeroCuenta) {
        repo.findByNumeroCuenta(numeroCuenta).ifPresent(repo::delete);
    }

    public List<Cuenta> findByClienteId(String clienteId) {
        return repo.findByClienteId(clienteId);
    }
}
