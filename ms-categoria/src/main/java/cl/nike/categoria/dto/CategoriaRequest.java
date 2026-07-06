package cl.nike.categoria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequest {

    @NotNull(message = "El id de la categoría es obligatorio")
    private BigDecimal idCategoria;

    @Size(max = 50)
    private String nombre;

    private BigDecimal idTipo;
}