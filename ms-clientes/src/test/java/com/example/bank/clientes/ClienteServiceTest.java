package com.example.bank.clientes;

import com.example.bank.clientes.model.Cliente;
import com.example.bank.clientes.repository.ClienteRepository;
import com.example.bank.clientes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("jose.lema");
        cliente.setNombre("Jose Lema");
        cliente.setContrasenia("1234");
        cliente.setEstado(true);

        when(repository.save(cliente)).thenReturn(cliente);

        Cliente result = service.create(cliente);

        assertNotNull(result);
        assertEquals("jose.lema", result.getClienteId());
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void testBuscarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("jose.lema");

        when(repository.findByClienteId("jose.lema")).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = service.findByClienteId("jose.lema");

        assertTrue(result.isPresent());
        assertEquals("jose.lema", result.get().getClienteId());
        verify(repository, times(1)).findByClienteId("jose.lema");
    }
}
