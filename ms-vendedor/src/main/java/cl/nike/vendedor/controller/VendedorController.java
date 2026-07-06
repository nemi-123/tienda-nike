package cl.nike.vendedor.controller;

import cl.nike.vendedor.dto.VendedorRequest;
import cl.nike.vendedor.dto.VendedorResponse;
import cl.nike.vendedor.service.VendedorService;
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
@RequestMapping("/api/v1/vendedores")
@Tag(name = "Vendedores", description = "API para la gestión de vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un VendedorResponse:
     * - self   → GET    /api/v1/vendedores/{id}
     * - update → PUT    /api/v1/vendedores/{id}
     * - delete → DELETE /api/v1/vendedores/{id}
     * - all    → GET    /api/v1/vendedores
     */
    private VendedorResponse addLinks(VendedorResponse vendedor) {
        BigDecimal id = vendedor.getIdVendedor(); // Tu ID usa BigDecimal

        vendedor.add(linkTo(methodOn(VendedorController.class).findById(id)).withSelfRel());

        vendedor.add(linkTo(methodOn(VendedorController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar vendedor"));

        vendedor.add(linkTo(methodOn(VendedorController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar vendedor"));

        vendedor.add(linkTo(methodOn(VendedorController.class).findAll())
                .withRel("all").withTitle("GET - Listado de vendedores"));

        return vendedor;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todos los vendedores", description = "Retorna una lista con soporte HATEOAS de todos los registros de vendedores")
    public ResponseEntity<CollectionModel<VendedorResponse>> findAll() {
        List<VendedorResponse> vendedores = vendedorService.findAll();
        vendedores.forEach(this::addLinks);

        CollectionModel<VendedorResponse> collection = CollectionModel.of(
                vendedores,
                linkTo(methodOn(VendedorController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vendedor por ID", description = "Busca los detalles de un vendedor específico mediante su ID")
    public ResponseEntity<VendedorResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(vendedorService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo vendedor", description = "Crea un nuevo registro de vendedor en el sistema")
    public ResponseEntity<VendedorResponse> create(@Valid @RequestBody VendedorRequest request) {
        VendedorResponse creado = addLinks(vendedorService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vendedor", description = "Modifica los datos de un vendedor existente")
    public ResponseEntity<VendedorResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody VendedorRequest request) {
        return ResponseEntity.ok(addLinks(vendedorService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar vendedor", description = "Elimina un registro de vendedor del sistema mediante su ID")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        vendedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}