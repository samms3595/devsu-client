package com.devsu.clientes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.devsu.clientes.enums.Genero;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan");
        persona.setApellidos("Pérez");
        persona.setIdentificacion("1234567890");
        persona.setGenero(Genero.MASCULINO);
        persona.setEdad(30);
        persona.setDireccion("Calle 123");
        persona.setTelefono("1234567890");
    }

    @Test
    void getId() {
        assertEquals(1L, persona.getId());
    }

    @Test
    void getNombre() {
        assertEquals("Juan", persona.getNombre());
    }

    @Test
    void getApellidos() {
        assertEquals("Pérez", persona.getApellidos());
    }

    @Test
    void getIdentificacion() {
        assertEquals("1234567890", persona.getIdentificacion());
    }

    @Test
    void getGenero() {
        assertEquals(Genero.MASCULINO, persona.getGenero());
    }

    @Test
    void getEdad() {
        assertEquals(30, persona.getEdad());
    }

    @Test
    void getDireccion() {
        assertEquals("Calle 123", persona.getDireccion());
    }

    @Test
    void getTelefono() {
        assertEquals("1234567890", persona.getTelefono());
    }

    @Test
    void setId() {
        persona.setId(2L);
        assertEquals(2L, persona.getId());
    }

    @Test
    void setNombre() {
        persona.setNombre("Carlos");
        assertEquals("Carlos", persona.getNombre());
    }

    @Test
    void setApellidos() {
        persona.setApellidos("Gómez");
        assertEquals("Gómez", persona.getApellidos());
    }

    @Test
    void setIdentificacion() {
        persona.setIdentificacion("0987654321");
        assertEquals("0987654321", persona.getIdentificacion());
    }

    @Test
    void setGenero() {
        persona.setGenero(Genero.FEMENINO);
        assertEquals(Genero.FEMENINO, persona.getGenero());
    }

    @Test
    void setEdad() {
        persona.setEdad(25);
        assertEquals(25, persona.getEdad());
    }

    @Test
    void setDireccion() {
        persona.setDireccion("Avenida 456");
        assertEquals("Avenida 456", persona.getDireccion());
    }

    @Test
    void setTelefono() {
        persona.setTelefono("0987654321");
        assertEquals("0987654321", persona.getTelefono());
    }
}
