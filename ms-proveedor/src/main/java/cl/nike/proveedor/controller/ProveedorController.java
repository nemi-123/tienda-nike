package cl.nike.proveedor.controller;

import cl.nike.proveedor.dto.ProveedorRequest;
import cl.nike.proveedor.dto.ProveedorResponse;
import cl.nike.proveedor.service.ProveedorService;
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
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "API para la gestión de proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un ProveedorResponse:
     * - self   → GET    /api/v1/proveedores/{id}
     * - update → PUT    /api/v1/proveedores/{id}
     * - delete → DELETE /api/v1/proveedores/{id}
     * - all    → GET    /api/v1/proveedores
     */
    private ProveedorResponse addLinks(ProveedorResponse proveedor) {
        BigDecimal id = proveedor.getIdProveedor(); // Tu ID usa BigDecimal

        proveedor.add(linkTo(methodOn(ProveedorController.class).findById(id)).withSelfRel());

        proveedor.add(linkTo(methodOn(ProveedorController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar proveedor"));

        proveedor.add(linkTo(methodOn(ProveedorController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar proveedor"));

        proveedor.add(linkTo(methodOn(ProveedorController.class).findAll())
                .withRel("all").withTitle("GET - Listado de proveedores"));

        return proveedor;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Retorna una lista con soporte HATEOAS de todos los proveedores registrados")
    public ResponseEntity<CollectionModel<ProveedorResponse>> findAll() {
        List<ProveedorResponse> proveedores = proveedorService.findAll();
        proveedores.forEach(this::addLinks);

        CollectionModel<ProveedorResponse> collection = CollectionModel.of(
                proveedores,
                linkTo(methodOn(ProveedorController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Busca los detalles de un proveedor específico mediante su ID")
    public ResponseEntity<ProveedorResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(proveedorService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo proveedor", description = "Crea un nuevo registro de proveedor en el sistema")
    public ResponseEntity<ProveedorResponse> create(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse creado = addLinks(proveedorService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Modifica los datos de un proveedor existente")
    public ResponseEntity<ProveedorResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody ProveedorRequest request) {
        return ResponseEntity.ok(addLinks(proveedorService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un registro de proveedor del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}