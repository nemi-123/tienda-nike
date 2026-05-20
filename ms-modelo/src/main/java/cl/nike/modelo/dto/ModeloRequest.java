package cl.nike.modelo.dto;

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

    // modelo
    @NotNull(message = "El id del modelo es obligatorio")
    private BigDecimal idModelo;

    @Size(max = 50, message = "El nombre del modelo no puede superar los 50 caracteres")
    private String nombreModelo;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    // marca
    @NotNull(message = "El id de la marca es obligatorio")
    private BigDecimal idMarca;

    // categoria
    @NotNull(message = "El id de la categoría es obligatorio")
    private BigDecimal idCategoria;

}