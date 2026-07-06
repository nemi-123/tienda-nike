package cl.nike.marca.controller;

import cl.nike.marca.dto.MarcaRequest;
import cl.nike.marca.dto.MarcaResponse;
import cl.nike.marca.service.MarcaService;
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
@RequestMapping("/api/v1/marcas")
@Tag(name = "Marcas", description = "API para la gestión de Marcas")
public class MarcaController {

    private final MarcaService marcaService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un MarcaResponse:
     * - self   → GET    /api/v1/marcas/{id}
     * - update → PUT    /api/v1/marcas/{id}
     * - delete → DELETE /api/v1/marcas/{id}
     * - all    → GET    /api/v1/marcas
     */
    private MarcaResponse addLinks(MarcaResponse marca) {
        BigDecimal id = marca.getIdMarca(); // Tu ID usa BigDecimal

        marca.add(linkTo(methodOn(MarcaController.class).findById(id)).withSelfRel());

        marca.add(linkTo(methodOn(MarcaController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar marca"));

        marca.add(linkTo(methodOn(MarcaController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar marca"));

        marca.add(linkTo(methodOn(MarcaController.class).findAll())
                .withRel("all").withTitle("GET - Listado de marcas"));

        return marca;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todas las marcas", description = "Retorna una lista con soporte HATEOAS de todos los registros de marcas")
    public ResponseEntity<CollectionModel<MarcaResponse>> findAll() {
        List<MarcaResponse> marcas = marcaService.findAll();
        marcas.forEach(this::addLinks);

        CollectionModel<MarcaResponse> collection = CollectionModel.of(
                marcas,
                linkTo(methodOn(MarcaController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener marca por ID", description = "Busca los detalles de una marca específica mediante su ID")
    public ResponseEntity<MarcaResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(marcaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva marca", description = "Crea un nuevo registro de marca en el sistema")
    public ResponseEntity<MarcaResponse> create(@Valid @RequestBody MarcaRequest request) {
        MarcaResponse creado = addLinks(marcaService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar marca", description = "Modifica los datos de una marca existente")
    public ResponseEntity<MarcaResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody MarcaRequest request) {
        return ResponseEntity.ok(addLinks(marcaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar marca", description = "Elimina un registro de marca del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}