package cl.nike.venta.controller;

import cl.nike.venta.dto.DetallevtaRequest;
import cl.nike.venta.dto.DetallevtaResponse;
import cl.nike.venta.service.DetallevtaService;
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
@RequestMapping("/api/detalles-venta")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Detalles de Venta", description = "API para la gestión de detalles de venta")
public class DetallevtaController {

    private final DetallevtaService detallevtaService;

    @GetMapping
    @Operation(summary = "Obtener todos los detalles de venta", description = "Retorna una lista completa con todos los registros de detalles de venta")
    public ResponseEntity<List<DetallevtaResponse>> getAll() {
        return ResponseEntity.ok(detallevtaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de venta por ID", description = "Busca los detalles de un registro específico mediante su ID")
    public ResponseEntity<DetallevtaResponse> getById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(detallevtaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo detalle de venta", description = "Crea un nuevo registro de detalle de venta en el sistema")
    public ResponseEntity<DetallevtaResponse> create(@Valid @RequestBody DetallevtaRequest request) {
        DetallevtaResponse nuevoDetalle = detallevtaService.create(request);
        return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar detalle de venta", description = "Modifica los datos de un detalle de venta existente")
    public ResponseEntity<DetallevtaResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody DetallevtaRequest request) {
        return ResponseEntity.ok(detallevtaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de venta", description = "Elimina un registro de detalle de venta del sistema mediante su ID")
    public ResponseEntity<Void> delete(@PathVariable BigDecimal id) {
        detallevtaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}