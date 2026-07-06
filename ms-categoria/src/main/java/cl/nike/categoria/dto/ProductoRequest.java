package cl.nike.categoria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {

    @NotNull(message = "El nombre es obligatorio")
    @Size(max = 50)
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotNull(message = "La categoría es obligatoria")
    private BigDecimal idCategoria;
}