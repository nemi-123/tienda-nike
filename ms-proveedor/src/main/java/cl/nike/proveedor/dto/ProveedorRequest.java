package cl.nike.proveedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorRequest {

    @NotNull(message = "El id del proveedor es obligatorio")
    private BigDecimal idProveedor;

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    @NotNull(message = "El id de la ciudad es obligatorio")
    private BigDecimal idCiudad; // Relación directa por ID para el Request
}