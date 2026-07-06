package cl.nike.categoria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoRequest {

    @NotNull
    private BigDecimal idTipo;

    @Size(max = 50)
    private String nombre;
}