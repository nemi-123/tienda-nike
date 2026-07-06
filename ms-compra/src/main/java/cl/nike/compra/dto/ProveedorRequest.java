package cl.nike.compra.dto;

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

    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String telefono;
}