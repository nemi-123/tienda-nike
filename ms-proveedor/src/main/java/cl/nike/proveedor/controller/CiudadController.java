package cl.nike.proveedor.controller;

import cl.nike.proveedor.dto.CiudadRequest;
import cl.nike.proveedor.dto.CiudadResponse;
import cl.nike.proveedor.service.CiudadService;
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
@RequestMapping("/api/v1/ciudades")
@Tag(name = "Ciudades", description = "API para la gestión de ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @GetMapping
    @Operation(summary = "Obtener todas las ciudades", description = "Retorna una lista completa con todos los registros de ciudades")
    public ResponseEntity<List<CiudadResponse>> findAll() {
        return ResponseEntity.ok(ciudadService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ciudad por ID", description = "Busca los detalles de una ciudad específica mediante su ID")
    public ResponseEntity<CiudadResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(ciudadService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva ciudad", description = "Crea un nuevo registro de ciudad en el sistema")
    public ResponseEntity<CiudadResponse> create(@Valid @RequestBody CiudadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar ciudad", description = "Modifica los datos de una ciudad existente")
    public ResponseEntity<CiudadResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody CiudadRequest request) {
        return ResponseEntity.ok(ciudadService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad", description = "Elimina un registro de ciudad del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        ciudadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}