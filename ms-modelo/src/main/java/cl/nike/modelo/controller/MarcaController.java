package cl.nike.modelo.controller;

import cl.nike.modelo.dto.MarcaRequest;
import cl.nike.modelo.dto.MarcaResponse;
import cl.nike.modelo.service.MarcaService;
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
@RequestMapping("/api/v1/marcas")
@Tag(name = "Marcas", description = "API para la gestión de marcas")
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    @Operation(summary = "Obtener todas las marcas", description = "Retorna una lista completa con todos los registros de marcas")
    public ResponseEntity<List<MarcaResponse>> findAll() {
        return ResponseEntity.ok(marcaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener marca por ID", description = "Busca los detalles de una marca específica mediante su ID")
    public ResponseEntity<MarcaResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(marcaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva marca", description = "Crea un nuevo registro de marca en el sistema")
    public ResponseEntity<MarcaResponse> create(@Valid @RequestBody MarcaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar marca", description = "Modifica los datos de una marca existente")
    public ResponseEntity<MarcaResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody MarcaRequest request) {
        return ResponseEntity.ok(marcaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar marca", description = "Elimina un registro de marca del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}