package cl.nike.cliente.controller;

import cl.nike.cliente.dto.ClienteRequest;
import cl.nike.cliente.dto.ClienteResponse;
import cl.nike.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    private ClienteResponse addLinks(ClienteResponse cliente) {
        BigDecimal id = cliente.getIdCliente();

        cliente.add(linkTo(methodOn(ClienteController.class).findById(id)).withSelfRel());
        cliente.add(linkTo(methodOn(ClienteController.class).update(id, null)).withRel("update"));
        cliente.add(linkTo(methodOn(ClienteController.class).deleteById(id)).withRel("delete"));
        cliente.add(linkTo(methodOn(ClienteController.class).findAll()).withRel("all"));

        return cliente;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista con soporte HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class))))
    })
    public ResponseEntity<CollectionModel<ClienteResponse>> findAll() {
        List<ClienteResponse> clientes = clienteService.findAll();
        clientes.forEach(this::addLinks);

        CollectionModel<ClienteResponse> collection = CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Busca un cliente específico por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    public ResponseEntity<ClienteResponse> findById(@Parameter(description = "ID del cliente", example = "1") @PathVariable @NonNull BigDecimal id) {
        return ResponseEntity.ok(addLinks(clienteService.findById(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo cliente", description = "Crea un nuevo registro de cliente")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse creado = addLinks(clienteService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Modifica los datos de un cliente existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente actualizado",
                    content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    public ResponseEntity<ClienteResponse> update(
            @Parameter(description = "ID del cliente", example = "1") @PathVariable @NonNull BigDecimal id, 
            @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(addLinks(clienteService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un registro mediante su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@Parameter(description = "ID del cliente", example = "1") @PathVariable @NonNull BigDecimal id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}