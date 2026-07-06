package cl.nike.compra.controller;

import cl.nike.compra.dto.CompraRequest;
import cl.nike.compra.dto.CompraResponse;
import cl.nike.compra.service.CompraService;
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
@RequestMapping("/api/v1/compras")
@Tag(name = "Compras", description = "API para la gestión de compras")
public class CompraController {

    private final CompraService compraService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un CompraResponse:
     * - self   → GET    /api/v1/compras/{id}
     * - update → PUT    /api/v1/compras/{id}
     * - delete → DELETE /api/v1/compras/{id}
     * - all    → GET    /api/v1/compras
     */
    private CompraResponse addLinks(CompraResponse compra) {
        BigDecimal id = compra.getIdCompra(); // Tu ID usa BigDecimal

        compra.add(linkTo(methodOn(CompraController.class).findById(id)).withSelfRel());

        compra.add(linkTo(methodOn(CompraController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar compra"));

        compra.add(linkTo(methodOn(CompraController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar compra"));

        compra.add(linkTo(methodOn(CompraController.class).findAll())
                .withRel("all").withTitle("GET - Listado de compras"));

        return compra;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todas las compras", description = "Retorna una lista con soporte HATEOAS de todos los registros de compras")
    public ResponseEntity<CollectionModel<CompraResponse>> findAll() {
        List<CompraResponse> compras = compraService.findAll();
        compras.forEach(this::addLinks);

        CollectionModel<CompraResponse> collection = CollectionModel.of(
                compras,
                linkTo(methodOn(CompraController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener compra por ID", description = "Busca los detalles de una compra específica mediante su ID")
    public ResponseEntity<CompraResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(compraService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva compra", description = "Crea un nuevo registro de compra en el sistema")
    public ResponseEntity<CompraResponse> create(@Valid @RequestBody CompraRequest request) {
        CompraResponse creado = addLinks(compraService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar compra", description = "Modifica los datos de una compra existente")
    public ResponseEntity<CompraResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody CompraRequest request) {
        return ResponseEntity.ok(addLinks(compraService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar compra", description = "Elimina un registro de compra del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        compraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}