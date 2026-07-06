package cl.nike.modelo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloRequest {

    @NotNull(message = "El id del modelo es obligatorio")
    private BigDecimal idModelo;

    @Size(max = 50)
    private String nombre;

    private BigDecimal precio;

    @NotNull(message = "El id de la marca es obligatorio")
    private BigDecimal idMarca;

    @NotNull(message = "El id de la categoría es obligatorio")
    private BigDecimal idCategoria;
}