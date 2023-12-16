package com.devsu.clientes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.devsu.clientes.enums.Estado;
import com.devsu.clientes.enums.Genero;
import com.devsu.clientes.exceptions.CustomHandlerException;
import com.devsu.clientes.model.Cliente;
import com.devsu.clientes.repository.ClienteRepository;
import com.devsu.clientes.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setClientId(123L);
        cliente.setNombre("Juan");
        cliente.setApellidos("Pérez");
        cliente.setIdentificacion("1234567890");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setEdad(30);
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("1234567890");
        cliente.setPassword("password123");
        cliente.setEstado(Estado.ACTIVO);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.guardarCliente(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(123L, resultado.getClientId());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testObtenerClientePorIdConExito() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.obtenerClientePorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }
    @Test
    void testObtenerClientePorIdNoEncontrado() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomHandlerException.class, () -> clienteService.obtenerClientePorId(id));
    }

    @Test
    void testEliminarCliente() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.eliminarCliente(id);

        verify(clienteRepository).deleteById(id);
    }
}
