package com.example.bank.clientes;

import com.example.bank.clientes.controller.ClienteController;
import com.example.bank.clientes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import com.example.bank.clientes.model.Cliente;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteService service;

    @Test
    void crearCliente_returnsCreated() throws Exception {
        when(service.create(any())).thenReturn(new Cliente());
        mvc.perform(post("/api/v1/clientes")
            .contentType("application/json")
            .content("{}"))
            .andExpect(status().isCreated());
    }
}
