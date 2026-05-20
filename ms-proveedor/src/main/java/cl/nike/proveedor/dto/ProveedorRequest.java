package cl.nike.proveedor.dto;

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

    // proveedor
    @NotNull(message = "El id del proveedor es obligatorio")
    private BigDecimal idProveedor;

    @Size(max = 50, message = "El nombre del proveedor no puede superar los 50 caracteres")
    private String nombreProveedor;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    // ciudad
    @NotNull(message = "El id de la ciudad es obligatorio")
    private BigDecimal idCiudad;

}