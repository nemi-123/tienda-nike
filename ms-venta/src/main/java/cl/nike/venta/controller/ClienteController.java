package cl.nike.venta.controller;

import cl.nike.venta.dto.ClienteRequest;
import cl.nike.venta.dto.ClienteResponse;
import cl.nike.venta.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista completa con todos los registros de clientes")
    public ResponseEntity<List<ClienteResponse>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Busca los detalles de un cliente específico mediante su ID")
    public ResponseEntity<ClienteResponse> getById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo cliente", description = "Crea un nuevo registro de cliente en el sistema")
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse nuevoCliente = clienteService.create(request);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Modifica los datos de un cliente existente")
    public ResponseEntity<ClienteResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un registro de cliente del sistema mediante su ID")
    public ResponseEntity<Void> delete(@PathVariable BigDecimal id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}