package com.devsu.clientes.controller;

import com.devsu.clientes.model.ApiError;
import com.devsu.clientes.model.Cliente;
import com.devsu.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "API para operaciones relacionadas con los clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Obtiene una lista de todos los clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obtiene un cliente por su ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Cliente no encontrado / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Obtiene un cliente por su ID de cliente", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Cliente no encontrado / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/client/{id}")
    public ResponseEntity<Cliente> obtenerClientePorClienteId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorClienteId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Guarda un nuevo cliente", responses = {
            @ApiResponse(responseCode = "201", description = "Cliente creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> guardarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteGuardado = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @Operation(summary = "Actualiza un cliente existente", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @Operation(summary = "Elimina un cliente por su ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta / Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> borrarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Cliente con el ID: " + id + " fue eliminado con éxito");
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
