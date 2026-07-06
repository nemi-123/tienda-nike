package cl.nike.detallevta.controller;

import cl.nike.detallevta.dto.DetallevtaRequest;
import cl.nike.detallevta.dto.DetallevtaResponse;
import cl.nike.detallevta.service.DetallevtaService;
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
@RequestMapping("/api/v1/detalles-venta")
@Tag(name = "Detalles ventas", description = "API para la gestión de Detalles Ventas")
public class DetallevtaController {

    private final DetallevtaService detalleService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un DetallevtaResponse:
     * - self   → GET    /api/v1/detalles-venta/{id}
     * - update → PUT    /api/v1/detalles-venta/{id}
     * - delete → DELETE /api/v1/detalles-venta/{id}
     * - all    → GET    /api/v1/detalles-venta
     */
    private DetallevtaResponse addLinks(DetallevtaResponse detalle) {
        BigDecimal id = detalle.getIdDetalle(); // Tu ID usa BigDecimal

        detalle.add(linkTo(methodOn(DetallevtaController.class).findById(id)).withSelfRel());

        detalle.add(linkTo(methodOn(DetallevtaController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar detalle de venta"));

        detalle.add(linkTo(methodOn(DetallevtaController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar detalle de venta"));

        detalle.add(linkTo(methodOn(DetallevtaController.class).findAll())
                .withRel("all").withTitle("GET - Listado de detalles de venta"));

        return detalle;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todos los detalles de ventas", description = "Retorna una lista con soporte HATEOAS de todos los registros de detalles de venta")
    public ResponseEntity<CollectionModel<DetallevtaResponse>> findAll() {
        List<DetallevtaResponse> detalles = detalleService.findAll();
        detalles.forEach(this::addLinks);

        CollectionModel<DetallevtaResponse> collection = CollectionModel.of(
                detalles,
                linkTo(methodOn(DetallevtaController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de venta por ID", description = "Busca los detalles de un registro específico mediante su ID")
    public ResponseEntity<DetallevtaResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(detalleService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo detalle de venta", description = "Crea un nuevo registro de detalle de venta en el sistema")
    public ResponseEntity<DetallevtaResponse> create(@Valid @RequestBody DetallevtaRequest request) {
        DetallevtaResponse creado = addLinks(detalleService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar detalle de venta", description = "Modifica los datos de un detalle de venta existente")
    public ResponseEntity<DetallevtaResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody DetallevtaRequest request) {
        return ResponseEntity.ok(addLinks(detalleService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de venta", description = "Elimina un registro de detalle de venta del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        detalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}