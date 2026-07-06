package cl.nike.detallevta.controller;

import cl.nike.detallevta.dto.VentaRequest;
import cl.nike.detallevta.dto.VentaResponse;
import cl.nike.detallevta.service.VentaService;
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
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "API para la gestión de ventas")
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    @Operation(summary = "Obtener todas las ventas", description = "Retorna una lista completa con todos los registros de ventas")
    public ResponseEntity<List<VentaResponse>> findAll() {
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Busca los detalles de una venta específica mediante su ID")
    public ResponseEntity<VentaResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(ventaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva venta", description = "Crea un nuevo registro de venta en el sistema")
    public ResponseEntity<VentaResponse> create(@Valid @RequestBody VentaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar venta", description = "Modifica los datos de una venta existente")
    public ResponseEntity<VentaResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody VentaRequest request) {
        return ResponseEntity.ok(ventaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venta", description = "Elimina un registro de venta del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}