package cl.nike.modelo.controller;

import cl.nike.modelo.dto.CategoriaRequest;
import cl.nike.modelo.dto.CategoriaResponse;
import cl.nike.modelo.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorías", description = "API para la gestión de categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", description = "Retorna una lista completa con todos los registros de categorías")
    public ResponseEntity<List<CategoriaResponse>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Busca los detalles de una categoría específica mediante su ID")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva categoría", description = "Crea un nuevo registro de categoría en el sistema")
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Modifica los datos de una categoría existente")
    public ResponseEntity<CategoriaResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina un registro de categoría del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}