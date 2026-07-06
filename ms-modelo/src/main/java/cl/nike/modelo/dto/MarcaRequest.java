package cl.nike.modelo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequest {

    @NotNull(message = "El id de la marca es obligatorio")
    private BigDecimal idMarca;

    @Size(max = 50)
    private String nombre;
}