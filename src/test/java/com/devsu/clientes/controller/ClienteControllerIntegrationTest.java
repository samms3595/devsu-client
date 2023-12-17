package com.devsu.clientes.controller;

import com.devsu.clientes.enums.Estado;
import com.devsu.clientes.enums.Genero;
import com.devsu.clientes.model.Cliente;
import com.devsu.clientes.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellidos("Pérez");
        cliente.setIdentificacion("1234567890");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setEdad(30);
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("1234567890");
        cliente.setClientId(123L);
        cliente.setPassword("miContraseña");
        cliente.setEstado(Estado.ACTIVO);

        clienteRepository.save(cliente);
    }

    @Test
    void obtenerClientesTest() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertFalse(clientes.isEmpty());
    }

    @Test
    void obtenerClientePorIdTest() {
        Optional<Cliente> cliente = clienteRepository.findById(1L);
        assertTrue(cliente.isPresent());
        assertEquals("Juan", cliente.get().getNombre());
    }


    @Test
    void guardarClienteTest() throws Exception {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setId(2L);
        nuevoCliente.setNombre("Ana");
        nuevoCliente.setApellidos("García");
        nuevoCliente.setIdentificacion("0987654321");
        nuevoCliente.setGenero(Genero.FEMENINO);
        nuevoCliente.setEdad(28);
        nuevoCliente.setDireccion("Avenida 456");
        nuevoCliente.setTelefono("0987654321");
        nuevoCliente.setClientId(456L);
        nuevoCliente.setPassword("contraseña123");
        nuevoCliente.setEstado(Estado.ACTIVO);

        clienteRepository.save(nuevoCliente);

        Optional<Cliente> clienteGuardado = clienteRepository.findById(nuevoCliente.getId());
        assertTrue(clienteGuardado.isPresent());
        assertEquals("Ana", clienteGuardado.get().getNombre());
    }

    @Test
    void actualizarClienteTest() {
        Cliente clienteActualizado = clienteRepository.findById(1L).orElseThrow();
        clienteActualizado.setNombre("Carlos");
        clienteRepository.save(clienteActualizado);

        Cliente clienteModificado = clienteRepository.findById(1L).orElseThrow();
        assertEquals("Carlos", clienteModificado.getNombre());
    }

    @Test
    void borrarClienteTest() {
        Long idCliente = 1L;
        assertTrue(clienteRepository.findById(idCliente).isPresent());
        clienteRepository.deleteById(idCliente);
        assertFalse(clienteRepository.findById(idCliente).isPresent());
    }
}
