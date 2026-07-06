package cl.nike.zapatilla.controller;

import cl.nike.zapatilla.dto.ModeloRequest;
import cl.nike.zapatilla.dto.ModeloResponse;
import cl.nike.zapatilla.service.ModeloService;
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
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Modelos", description = "API para la gestión de modelos")
public class ModeloController {

    private final ModeloService modeloService;

    @GetMapping
    @Operation(summary = "Obtener todos los modelos", description = "Retorna una lista completa con todos los registros de modelos")
    public ResponseEntity<List<ModeloResponse>> getAll() {
        return ResponseEntity.ok(modeloService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener modelo por ID", description = "Busca los detalles de un modelo específico mediante su ID")
    public ResponseEntity<ModeloResponse> getById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(modeloService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo modelo", description = "Crea un nuevo registro de modelo en el sistema")
    public ResponseEntity<ModeloResponse> create(@Valid @RequestBody ModeloRequest request) {
        ModeloResponse nuevoModelo = modeloService.create(request);
        return new ResponseEntity<>(nuevoModelo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar modelo", description = "Modifica los datos de un modelo existente")
    public ResponseEntity<ModeloResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody ModeloRequest request) {
        return ResponseEntity.ok(modeloService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar modelo", description = "Elimina un registro de modelo del sistema mediante su ID")
    public ResponseEntity<Void> delete(@PathVariable BigDecimal id) {
        modeloService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}