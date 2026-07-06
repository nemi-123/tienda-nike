package cl.nike.compra.controller;

import cl.nike.compra.dto.DetallecmpRequest;
import cl.nike.compra.dto.DetallecmpResponse;
import cl.nike.compra.service.DetallecmpService;
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
@RequestMapping("/api/v1/detalles-compra")
@Tag(name = "Detalles de Compra", description = "API para la gestión de detalles de compra")
public class DetallecmpController {

    private final DetallecmpService detalleService;

    @GetMapping
    @Operation(summary = "Obtener todos los detalles de compra", description = "Retorna una lista completa con todos los registros de detalles de compra")
    public ResponseEntity<List<DetallecmpResponse>> findAll() {
        return ResponseEntity.ok(detalleService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de compra por ID", description = "Busca los detalles de un registro específico mediante su ID")
    public ResponseEntity<DetallecmpResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(detalleService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo detalle de compra", description = "Crea un nuevo registro de detalle de compra en el sistema")
    public ResponseEntity<DetallecmpResponse> create(@Valid @RequestBody DetallecmpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar detalle de compra", description = "Modifica los datos de un detalle de compra existente")
    public ResponseEntity<DetallecmpResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody DetallecmpRequest request) {
        return ResponseEntity.ok(detalleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de compra", description = "Elimina un registro de detalle de compra del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        detalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}