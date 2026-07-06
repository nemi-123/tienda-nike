package cl.nike.proveedor.controller;

import cl.nike.proveedor.dto.CompraRequest;
import cl.nike.proveedor.dto.CompraResponse;
import cl.nike.proveedor.service.CompraService;
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
@RequestMapping("/api/v1/compras")
@Tag(name = "Compras", description = "API para la gestión de compras")
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    @Operation(summary = "Obtener todas las compras", description = "Retorna una lista completa con todos los registros de compras")
    public ResponseEntity<List<CompraResponse>> findAll() {
        return ResponseEntity.ok(compraService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener compra por ID", description = "Busca los detalles de una compra específica mediante su ID")
    public ResponseEntity<CompraResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(compraService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva compra", description = "Crea un nuevo registro de compra en el sistema")
    public ResponseEntity<CompraResponse> create(@Valid @RequestBody CompraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compraService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar compra", description = "Modifica los datos de una compra existente")
    public ResponseEntity<CompraResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar compra", description = "Elimina un registro de compra del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        compraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}