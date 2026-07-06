package cl.nike.compra.controller;

import cl.nike.compra.dto.ProveedorRequest;
import cl.nike.compra.dto.ProveedorResponse;
import cl.nike.compra.service.ProveedorService;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "API para la gestión de proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Retorna una lista completa con todos los proveedores registrados")
    public ResponseEntity<List<ProveedorResponse>> findAll() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Busca los detalles de un proveedor específico mediante su ID")
    public ResponseEntity<ProveedorResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(proveedorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo proveedor", description = "Crea un nuevo registro de proveedor en el sistema")
    public ResponseEntity<ProveedorResponse> create(@Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Modifica los datos de un proveedor existente")
    public ResponseEntity<ProveedorResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.ok(proveedorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un registro de proveedor del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}