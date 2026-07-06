package cl.nike.cliente.controller;

import cl.nike.cliente.dto.CompracliRequest;
import cl.nike.cliente.dto.CompracliResponse;
import cl.nike.cliente.service.CompracliService;
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
@Tag(name = "Compras", description = "API para la gestión de compras de clientes")
public class CompracliController {

    private final CompracliService compracliService;

    @GetMapping
    @Operation(summary = "Obtener todas las compras", description = "Retorna una lista con todas las compras registradas")
    public ResponseEntity<List<CompracliResponse>> findAll() {
        return ResponseEntity.ok(compracliService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener compra por ID", description = "Busca una compra específica por su identificador")
    public ResponseEntity<CompracliResponse> findById(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(compracliService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva compra", description = "Crea un nuevo registro de compra en el sistema")
    public ResponseEntity<CompracliResponse> create(@Valid @RequestBody CompracliRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compracliService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar compra", description = "Modifica los datos de una compra existente")
    public ResponseEntity<CompracliResponse> update(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody CompracliRequest request) {
        return ResponseEntity.ok(compracliService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar compra", description = "Elimina un registro de compra mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable BigDecimal id) {
        compracliService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}