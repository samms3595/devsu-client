package com.devsu.clientes.model;

import com.devsu.clientes.enums.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClientId(123L);
        cliente.setPassword("miContraseña");
        cliente.setEstado(Estado.ACTIVO);
    }

    @Test
    void getClientId() {
        assertEquals(123L, cliente.getClientId());
    }

    @Test
    void getPassword() {
        assertEquals("miContraseña", cliente.getPassword());
    }

    @Test
    void getEstado() {
        assertEquals(Estado.ACTIVO, cliente.getEstado());
    }

    @Test
    void setClientId() {
        cliente.setClientId(456L);
        assertEquals(456L, cliente.getClientId());
    }

    @Test
    void setPassword() {
        cliente.setPassword("nuevaContraseña");
        assertEquals("nuevaContraseña", cliente.getPassword());
    }

    @Test
    void setEstado() {
        cliente.setEstado(Estado.INACTIVO);
        assertEquals(Estado.INACTIVO, cliente.getEstado());
    }
}
