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

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        if(this.clienteRepository.findById(cliente.getId())
                .isPresent()) throw new CustomHandlerException("Cliente con el ID: " + cliente.getId()
                + " Ya se encuentra registrado");
        try{
            return this.clienteRepository.save(cliente);
        }catch (DataIntegrityViolationException e) {
            throw new SQLCustomException(e.getMessage(), e);
        }
        catch (Exception e){
            throw new CustomHandlerException("Error al guardar el cliente: " + e.getMessage());
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return this.clienteRepository.findById(cliente.getId())
                .map(clienteExistente -> {
                    clienteExistente.setId(cliente.getId());
                    clienteExistente.setClientId(cliente.getClientId());
                    clienteExistente.setNombre(cliente.getNombre());
                    clienteExistente.setApellidos(cliente.getApellidos());
                    clienteExistente.setGenero(cliente.getGenero());
                    clienteExistente.setEdad(cliente.getEdad());
                    clienteExistente.setDireccion(cliente.getDireccion());
                    clienteExistente.setTelefono(cliente.getTelefono());
                    clienteExistente.setPassword(cliente.getPassword());
                    clienteExistente.setEstado(cliente.getEstado());

                    return this.clienteRepository.save(clienteExistente);
                }).orElseThrow(()-> new CustomHandlerException("Cliente no Encontrado: " +  cliente.getId()));
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                ()-> new CustomHandlerException("No se encotró al cliente con el ID: " + id));
    }

    @Override
    public Cliente obtenerClientePorClienteId(Long id) {
        return this.clienteRepository.getClienteByClientId(id)
                .orElseThrow(()-> new CustomHandlerException("Cliente con el id: " + id +  " No encontrado"));
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> clienteList = this.clienteRepository.findAll();
        if(clienteList.isEmpty()) throw new CustomHandlerException("No hay datos a mostrar");
        return clienteList;
    }

    @Override
    public boolean eliminarCliente(Long id) {
        Cliente cliente = this.clienteRepository.findById(id)
                .orElseThrow(()-> new CustomHandlerException("Cliente no encontrado: " +  id));
        this.clienteRepository.delete(cliente);
        return true;
    }
}
