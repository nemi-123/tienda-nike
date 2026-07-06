package cl.nike.venta.controller;

import cl.nike.venta.dto.VentaRequest;
import cl.nike.venta.dto.VentaResponse;
import cl.nike.venta.service.VentaService;
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
@RequestMapping("/api/v1/ventas")
@CrossOrigin(origins = "*") 
@RequiredArgsConstructor 
@Tag(name = "Ventas", description = "API para la gestión de ventas")
public class VentaController {

    private final VentaService ventaService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un VentaResponse:
     * - self   → GET    /api/v1/ventas/{id}
     * - update → PUT    /api/v1/ventas/{id}
     * - delete → DELETE /api/v1/ventas/{id}
     * - all    → GET    /api/v1/ventas
     */
    private VentaResponse addLinks(VentaResponse venta) {
        BigDecimal id = venta.getIdventa(); // Tu ID usa BigDecimal

        venta.add(linkTo(methodOn(VentaController.class).getById(id)).withSelfRel());

        venta.add(linkTo(methodOn(VentaController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar venta"));

        venta.add(linkTo(methodOn(VentaController.class).delete(id))
                .withRel("delete").withTitle("DELETE - Eliminar venta"));

        venta.add(linkTo(methodOn(VentaController.class).getAll())
                .withRel("all").withTitle("GET - Listado de ventas"));

        return venta;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todas las ventas", description = "Retorna una lista con soporte HATEOAS de todos los registros de ventas")
    public ResponseEntity<CollectionModel<VentaResponse>> getAll() {
        List<VentaResponse> ventas = ventaService.findAll();
        ventas.forEach(this::addLinks);

        CollectionModel<VentaResponse> collection = CollectionModel.of(
                ventas,
                linkTo(methodOn(VentaController.class).getAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Busca los detalles de una venta específica mediante su ID")
    public ResponseEntity<VentaResponse> getById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(ventaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva venta", description = "Crea un nuevo registro de venta en el sistema")
    public ResponseEntity<VentaResponse> create(@Valid @RequestBody VentaRequest request) {
        VentaResponse nuevaVenta = addLinks(ventaService.create(request));
        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar venta", description = "Modifica los datos de una venta existente")
    public ResponseEntity<VentaResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody VentaRequest request) {
        return ResponseEntity.ok(addLinks(ventaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venta", description = "Elimina un registro de venta del sistema mediante su ID")
    public ResponseEntity<Void> delete(@PathVariable @NonNull BigDecimal id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}