package com.example.bank.cuentas;

import com.example.bank.cuentas.controller.MovimientoController;
import com.example.bank.cuentas.model.Cuenta;
import com.example.bank.cuentas.model.Movimiento;
import com.example.bank.cuentas.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimientoControllerTest {

    @InjectMocks
    private MovimientoController controller;

    @Mock
    private MovimientoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipoMovimiento("DEPOSITO");
        movimiento.setValor(BigDecimal.valueOf(100));
        movimiento.setSaldo(BigDecimal.valueOf(100));
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setCuenta(new Cuenta());

        when(service.create(any(Movimiento.class))).thenReturn(movimiento);

        ResponseEntity<Movimiento> response = controller.crear(movimiento);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testObtenerPorCuenta() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(2L);
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(BigDecimal.valueOf(50));
        movimiento.setSaldo(BigDecimal.valueOf(50));
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setCuenta(new Cuenta());

        when(service.findByNumeroCuenta("123")).thenReturn(List.of(movimiento));

        ResponseEntity<List<Movimiento>> response = controller.obtenerPorCuenta("123");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(2L, response.getBody().get(0).getId());
    }

    @Test
    void testActualizarMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(3L);
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(BigDecimal.valueOf(30));
        movimiento.setSaldo(BigDecimal.valueOf(70));
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setCuenta(new Cuenta());

        when(service.update(eq(3L), any(Movimiento.class))).thenReturn(Optional.of(movimiento));

        ResponseEntity<Movimiento> response = controller.actualizar(3L, movimiento);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3L, response.getBody().getId());
    }


}
