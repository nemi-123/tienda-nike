package cl.nike.vendedor.controller;

import cl.nike.vendedor.dto.SucursalRequest;
import cl.nike.vendedor.dto.SucursalResponse;
import cl.nike.vendedor.service.SucursalService;
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
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursales", description = "API para la gestión de sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales", description = "Retorna una lista completa con todos los registros de sucursales")
    public ResponseEntity<List<SucursalResponse>> findAll() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sucursal por ID", description = "Busca los detalles de una sucursal específica mediante su ID")
    public ResponseEntity<SucursalResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(sucursalService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva sucursal", description = "Crea un nuevo registro de sucursal en el sistema")
    public ResponseEntity<SucursalResponse> create(@Valid @RequestBody SucursalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar sucursal", description = "Modifica los datos de una sucursal existente")
    public ResponseEntity<SucursalResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody SucursalRequest request) {
        return ResponseEntity.ok(sucursalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sucursal", description = "Elimina un registro de sucursal del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        sucursalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}