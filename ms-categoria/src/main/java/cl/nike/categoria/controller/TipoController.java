package cl.nike.categoria.controller;

import cl.nike.categoria.dto.TipoRequest;
import cl.nike.categoria.dto.TipoResponse;
import cl.nike.categoria.service.TipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos")
@RequiredArgsConstructor
public class TipoController {

    private final TipoService tipoService;

    @GetMapping
    public ResponseEntity<List<TipoResponse>> listarTodos() {
        return ResponseEntity.ok(tipoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoResponse> buscarPorId(@PathVariable BigDecimal id) {
        return ResponseEntity.ok(tipoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoResponse> crear(@Valid @RequestBody TipoRequest request) {
        TipoResponse nuevoTipo = tipoService.crear(request);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoResponse> actualizar(
            @PathVariable BigDecimal id, 
            @Valid @RequestBody TipoRequest request) {
        return ResponseEntity.ok(tipoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable BigDecimal id) {
        tipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}