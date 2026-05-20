package cl.nike.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloResponse {

    // modelo
    private BigDecimal idModelo;
    private String nombreModelo;
    private BigDecimal precio;

    // marca
    private BigDecimal idMarca;
    private String nombreMarca;

    // categoria
    private BigDecimal idCategoria;
    private String nombreCategoria;

}