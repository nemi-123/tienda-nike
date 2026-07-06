package cl.nike.marca.controller;

import cl.nike.marca.dto.PaisRequest;
import cl.nike.marca.dto.PaisResponse;
import cl.nike.marca.service.PaisService;
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
@RequestMapping("/api/v1/paises")
@Tag(name = "Países", description = "API para la gestión de países")
public class PaisController {

    private final PaisService paisService;

    @GetMapping
    @Operation(summary = "Obtener todos los países", description = "Retorna una lista completa con todos los países registrados en el sistema")
    public ResponseEntity<List<PaisResponse>> findAll() {
        return ResponseEntity.ok(paisService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener país por ID", description = "Busca los detalles de un país específico mediante su ID")
    public ResponseEntity<PaisResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(paisService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo país", description = "Crea un nuevo registro de país en el sistema")
    public ResponseEntity<PaisResponse> create(@Valid @RequestBody PaisRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar país", description = "Modifica los datos de un país existente")
    public ResponseEntity<PaisResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody PaisRequest request) {
        return ResponseEntity.ok(paisService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar país", description = "Elimina un registro de país del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        paisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}