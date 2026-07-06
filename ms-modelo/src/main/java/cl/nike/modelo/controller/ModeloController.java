package cl.nike.modelo.controller;

import cl.nike.modelo.dto.ModeloRequest;
import cl.nike.modelo.dto.ModeloResponse;
import cl.nike.modelo.service.ModeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modelos")
@Tag(name = "Modelos", description = "API para la gestión de modelos")
public class ModeloController {

    private final ModeloService modeloService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un ModeloResponse:
     * - self   → GET    /api/v1/modelos/{id}
     * - update → PUT    /api/v1/modelos/{id}
     * - delete → DELETE /api/v1/modelos/{id}
     * - all    → GET    /api/v1/modelos
     */
    private ModeloResponse addLinks(ModeloResponse modelo) {
        BigDecimal id = modelo.getIdModelo(); // Tu ID usa BigDecimal

        modelo.add(linkTo(methodOn(ModeloController.class).findById(id)).withSelfRel());

        modelo.add(linkTo(methodOn(ModeloController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar modelo"));

        modelo.add(linkTo(methodOn(ModeloController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar modelo"));

        modelo.add(linkTo(methodOn(ModeloController.class).findAll())
                .withRel("all").withTitle("GET - Listado de modelos"));

        return modelo;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todos los modelos", description = "Retorna una lista con soporte HATEOAS de todos los registros de modelos")
    public ResponseEntity<CollectionModel<ModeloResponse>> findAll() {
        List<ModeloResponse> modelos = modeloService.findAll();
        modelos.forEach(this::addLinks);

        CollectionModel<ModeloResponse> collection = CollectionModel.of(
                modelos,
                linkTo(methodOn(ModeloController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener modelo por ID", description = "Busca los detalles de un modelo específico mediante su ID")
    public ResponseEntity<ModeloResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(modeloService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo modelo", description = "Crea un nuevo registro de modelo en el sistema")
    public ResponseEntity<ModeloResponse> create(@Valid @RequestBody ModeloRequest request) {
        ModeloResponse creado = addLinks(modeloService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar modelo", description = "Modifica los datos de un modelo existente")
    public ResponseEntity<ModeloResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody ModeloRequest request) {
        return ResponseEntity.ok(addLinks(modeloService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar modelo", description = "Elimina un registro de modelo del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        modeloService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}