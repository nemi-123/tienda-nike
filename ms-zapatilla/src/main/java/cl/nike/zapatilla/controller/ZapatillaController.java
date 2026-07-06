package cl.nike.zapatilla.controller;

import cl.nike.zapatilla.dto.ZapatillaRequest;
import cl.nike.zapatilla.dto.ZapatillaResponse;
import cl.nike.zapatilla.service.ZapatillaService;
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
@RequestMapping("/api/v1/zapatillas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Zapatillas", description = "API para la gestión de zapatillas")
public class ZapatillaController {

    private final ZapatillaService zapatillaService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un ZapatillaResponse:
     * - self   → GET    /api/v1/zapatillas/{id}
     * - update → PUT    /api/v1/zapatillas/{id}
     * - delete → DELETE /api/v1/zapatillas/{id}
     * - all    → GET    /api/v1/zapatillas
     */
    private ZapatillaResponse addLinks(ZapatillaResponse zapatilla) {
        BigDecimal id = zapatilla.getIdzapatilla(); // Tu ID usa BigDecimal

        zapatilla.add(linkTo(methodOn(ZapatillaController.class).getById(id)).withSelfRel());

        zapatilla.add(linkTo(methodOn(ZapatillaController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar zapatilla"));

        zapatilla.add(linkTo(methodOn(ZapatillaController.class).delete(id))
                .withRel("delete").withTitle("DELETE - Eliminar zapatilla"));

        zapatilla.add(linkTo(methodOn(ZapatillaController.class).getAll())
                .withRel("all").withTitle("GET - Listado de zapatillas"));

        return zapatilla;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todas las zapatillas", description = "Retorna una lista con soporte HATEOAS de todos los registros de zapatillas")
    public ResponseEntity<CollectionModel<ZapatillaResponse>> getAll() {
        List<ZapatillaResponse> zapatillas = zapatillaService.findAll();
        zapatillas.forEach(this::addLinks);

        CollectionModel<ZapatillaResponse> collection = CollectionModel.of(
                zapatillas,
                linkTo(methodOn(ZapatillaController.class).getAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener zapatilla por ID", description = "Busca los detalles de una zapatilla específica mediante su ID")
    public ResponseEntity<ZapatillaResponse> getById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(zapatillaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva zapatilla", description = "Crea un nuevo registro de zapatilla en el sistema")
    public ResponseEntity<ZapatillaResponse> create(@Valid @RequestBody ZapatillaRequest request) {
        ZapatillaResponse nuevaZapatilla = addLinks(zapatillaService.create(request));
        return new ResponseEntity<>(nuevaZapatilla, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar zapatilla", description = "Modifica los datos de una zapatilla existente")
    public ResponseEntity<ZapatillaResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody ZapatillaRequest request) {
        return ResponseEntity.ok(addLinks(zapatillaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar zapatilla", description = "Elimina un registro de zapatilla del sistema mediante su ID")
    public ResponseEntity<Void> delete(@PathVariable @NonNull BigDecimal id) {
        zapatillaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}