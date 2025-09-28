package com.example.bank.cuentas.controller;

import com.example.bank.cuentas.model.Movimiento;
import com.example.bank.cuentas.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Movimiento> crear(@RequestBody Movimiento movimiento) {
        Movimiento saved = service.create(movimiento);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<List<Movimiento>> obtenerPorCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(service.findByNumeroCuenta(numeroCuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(@PathVariable Long id,
                                                 @RequestBody Movimiento movimiento) {
        return service.update(id, movimiento)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
