package cl.nike.categoria.dto;

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
public class ProductoRequest {
            // Producto
    @NotNull(message = "El id del producto es obligatorio")
    private BigDecimal idProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombreProducto;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

}
