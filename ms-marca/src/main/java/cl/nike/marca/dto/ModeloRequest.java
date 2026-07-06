package cl.nike.marca.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloRequest {

    @NotNull(message = "El id del modelo es obligatorio")
    private BigDecimal idModelo;

    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;

    private BigDecimal precio;

    @NotNull(message = "El id de la marca es obligatorio")
    private BigDecimal idMarca;
}