package com.example.bank.clientes.controller;

import com.example.bank.clientes.model.Cliente;
import com.example.bank.clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        Cliente saved = service.create(cliente);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> obtener(@PathVariable String clienteId) {
        return service.findByClienteId(clienteId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> actualizar(@PathVariable String clienteId, @RequestBody Cliente cliente) {
        return service.update(clienteId, cliente)
                .map(ResponseEntity::ok) // ✅ ahora usamos Optional correctamente
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> eliminar(@PathVariable String clienteId) {
        boolean deleted = service.delete(clienteId);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
