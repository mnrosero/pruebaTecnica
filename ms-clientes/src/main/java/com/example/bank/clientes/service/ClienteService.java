package com.example.bank.clientes.service;

import com.example.bank.clientes.model.Cliente;
import com.example.bank.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Cliente create(Cliente c) {
        return repo.save(c);
    }

    public Optional<Cliente> findByClienteId(String clienteId) {
        return repo.findByClienteId(clienteId);
    }

    @Transactional
    public Optional<Cliente> update(String clienteId, Cliente cliente) {
        return repo.findByClienteId(clienteId).map(existing -> {
            existing.setNombre(cliente.getNombre());
            existing.setGenero(cliente.getGenero());
            existing.setEdad(cliente.getEdad());
            existing.setIdentificacion(cliente.getIdentificacion());
            existing.setDireccion(cliente.getDireccion());
            existing.setTelefono(cliente.getTelefono());
            existing.setContrasenia(cliente.getContrasenia());
            existing.setEstado(cliente.isEstado());
            return repo.save(existing);
        });
    }

    @Transactional
    public boolean delete(String clienteId) {
        return repo.findByClienteId(clienteId).map(existing -> {
            repo.delete(existing);
            return true;
        }).orElse(false);
    }
}
