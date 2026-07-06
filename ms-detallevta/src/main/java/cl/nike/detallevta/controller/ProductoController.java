package cl.nike.detallevta.controller;

import cl.nike.detallevta.dto.ProductoRequest;
import cl.nike.detallevta.dto.ProductoResponse;
import cl.nike.detallevta.service.ProductoService;
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
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista completa con todos los productos registrados")
    public ResponseEntity<List<ProductoResponse>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Busca los detalles de un producto específico mediante su ID")
    public ResponseEntity<ProductoResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo producto", description = "Crea un nuevo registro de producto en el sistema")
    public ResponseEntity<ProductoResponse> create(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Modifica los datos de un producto existente")
    public ResponseEntity<ProductoResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un registro de producto del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}