package cl.nike.detallevta.dto;

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

    @NotNull(message = "El id del producto es obligatorio")
    private BigDecimal idProducto;

    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;

    private BigDecimal precio;
}