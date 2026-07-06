package cl.nike.categoria.controller;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;
import cl.nike.categoria.service.TipoService;
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
@RequestMapping("/api/v1/tipos")
@Tag(name = "Tipos", description = "API para la gestión de tipos de productos")
public class TipoController {

    private final TipoService tipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los tipos", description = "Retorna una lista con todos los tipos registrados")
    public ResponseEntity<List<TipoResponse>> findAll() {
        return ResponseEntity.ok(tipoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo por ID", description = "Busca los detalles de un tipo específico mediante su ID")
    public ResponseEntity<TipoResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(tipoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo tipo", description = "Crea un nuevo registro de tipo en el sistema")
    public ResponseEntity<TipoResponse> create(@Valid @RequestBody TipoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo", description = "Modifica los datos de un tipo existente en el sistema")
    public ResponseEntity<TipoResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody TipoRequest request) {
        return ResponseEntity.ok(tipoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo", description = "Elimina un registro de tipo mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        tipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}