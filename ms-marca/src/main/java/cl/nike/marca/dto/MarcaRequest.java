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
public class MarcaRequest {

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

    @Size(max = 50, message = "El nombre de la marca no puede superar los 50 caracteres")
    private String nombreMarca;

    // pais
    @NotNull(message = "El id del país es obligatorio")
    private BigDecimal idPais;

    @Size(max = 50, message = "El nombre del país no puede superar los 50 caracteres")
    private String nombrePais;

}