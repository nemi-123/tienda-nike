package cl.nike.categoria.controller;

import cl.nike.categoria.dto.CategoriaRequest;
import cl.nike.categoria.dto.CategoriaResponse;
import cl.nike.categoria.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "API para la gestión de categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un CategoriaResponse:
     *   - self   → GET    /api/v1/categorias/{id}
     *   - update → PUT    /api/v1/categorias/{id}
     *   - delete → DELETE /api/v1/categorias/{id}
     *   - all    → GET    /api/v1/categorias
     */
    private CategoriaResponse addLinks(CategoriaResponse categoria) {
        BigDecimal id = categoria.getIdCategoria(); // Tu ID usa BigDecimal

        categoria.add(linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel());

        categoria.add(linkTo(methodOn(CategoriaController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar categoría"));

        categoria.add(linkTo(methodOn(CategoriaController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar categoría"));

        categoria.add(linkTo(methodOn(CategoriaController.class).findAll())
                .withRel("all").withTitle("GET - Listado de categorías"));

        return categoria;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", description = "Retorna una lista con soporte HATEOAS de todas las categorías")
    public ResponseEntity<CollectionModel<CategoriaResponse>> findAll() {
        List<CategoriaResponse> categorias = categoriaService.findAll();
        categorias.forEach(this::addLinks);

        CollectionModel<CategoriaResponse> collection = CollectionModel.of(
                categorias,
                linkTo(methodOn(CategoriaController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID", description = "Busca los detalles de una categoría específica mediante su ID")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(categoriaService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nueva categoría", description = "Crea un nuevo registro de categoría en el sistema")
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse creado = addLinks(categoriaService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Modifica la información de una categoría existente")
    public ResponseEntity<CategoriaResponse> update(
            @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(addLinks(categoriaService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina un registro de categoría del sistema permanentemente")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull BigDecimal id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}