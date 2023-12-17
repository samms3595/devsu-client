package com.devsu.clientes.service;

import com.devsu.clientes.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    Cliente obtenerClientePorId(Long id);
    Cliente obtenerClientePorClienteId(Long id);
    List<Cliente> obtenerTodosClientes();
    boolean eliminarCliente(Long id);
    boolean añadirCuenta(Long idCliente, Long idCuenta);
    boolean eliminarCuenta(Long idCliente, Long idCuenta);
}
