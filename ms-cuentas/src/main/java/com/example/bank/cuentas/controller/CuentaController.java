package com.example.bank.cuentas.controller;

import com.example.bank.cuentas.model.Cuenta;
import com.example.bank.cuentas.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        Cuenta saved = service.create(cuenta);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtener(@PathVariable String numeroCuenta) {
        return service.findByNumeroCuenta(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable String numeroCuenta,
                                             @RequestBody Cuenta cuenta) {
        return service.update(numeroCuenta, cuenta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminar(@PathVariable String numeroCuenta) {
        service.delete(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}
