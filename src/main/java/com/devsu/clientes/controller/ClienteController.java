package com.devsu.clientes.controller;

import com.devsu.clientes.model.Cliente;
import com.devsu.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        return new ResponseEntity<>(this.clienteService.obtenerTodosClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClienteporId(@PathVariable Long id){
        return new ResponseEntity<>(this.clienteService.obtenerClientePorId(id), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Cliente> obtenerClienteporClienteId(@PathVariable Long id){
        return new ResponseEntity<>(this.clienteService.obtenerClientePorClienteId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(this.clienteService.guardarCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCliente(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        if(this.clienteService.eliminarCliente(id)){
            response.put("mensaje", "Cliente con el ID: "  + id + " Fue eliminado con exito");
            response.put("timestamp", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(this.clienteService.actualizarCliente(cliente), HttpStatus.OK);
    }
}
