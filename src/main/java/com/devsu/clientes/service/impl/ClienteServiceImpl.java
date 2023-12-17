package com.devsu.clientes.service.impl;

import com.devsu.clientes.exceptions.CustomHandlerException;
import com.devsu.clientes.exceptions.SQLCustomException;
import com.devsu.clientes.model.Cliente;
import com.devsu.clientes.repository.ClienteRepository;
import com.devsu.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            throw new CustomHandlerException("Cliente con el ID: " + cliente.getId() + " ya se encuentra registrado");
        }
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new SQLCustomException("Error de integridad de datos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new CustomHandlerException("Error al guardar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.findById(cliente.getId())
                .map(clienteExistente -> {
                    copiarPropiedadesCliente(clienteExistente, cliente);
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new CustomHandlerException("Cliente no encontrado: " + cliente.getId()));
    }

    private void copiarPropiedadesCliente(Cliente existente, Cliente actualizado) {
        existente.setNombre(actualizado.getNombre());
        existente.setApellidos(actualizado.getApellidos());
        existente.setGenero(actualizado.getGenero());
        existente.setEdad(actualizado.getEdad());
        existente.setDireccion(actualizado.getDireccion());
        existente.setTelefono(actualizado.getTelefono());
        existente.setPassword(actualizado.getPassword());
        existente.setEstado(actualizado.getEstado());
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new CustomHandlerException("Cliente con el ID: " + id + " no encontrado"));
    }

    @Override
    public Cliente obtenerClientePorClienteId(Long clienteId) {
        return clienteRepository.getClienteByClientId(clienteId)
                .orElseThrow(() -> new CustomHandlerException("Cliente con el clienteId: " + clienteId + " no encontrado"));
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new CustomHandlerException("No hay clientes disponibles");
        }
        return clientes;
    }

    @Override
    public boolean eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new CustomHandlerException("Cliente con el ID: " + id + " no encontrado");
        }
        clienteRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean añadirCuenta(Long idCliente, Long idCuenta) {
        Cliente cliente = this.obtenerClientePorClienteId(idCliente);
        cliente.agregarCuenta(idCuenta);
        try{
            this.actualizarCliente(cliente);
            return true;
        }catch (Exception e){
            throw new CustomHandlerException("Error al añadir cuenta");
        }
    }

    @Override
    public boolean eliminarCuenta(Long idCliente, Long idCuenta) {
        Cliente cliente = this.obtenerClientePorClienteId(idCliente);
        cliente.removerCuenta(idCuenta);
        try{
            this.actualizarCliente(cliente);
            return true;
        }catch (Exception e){
            throw new CustomHandlerException("Error al eliminar cuenta");
        }
    }
}
