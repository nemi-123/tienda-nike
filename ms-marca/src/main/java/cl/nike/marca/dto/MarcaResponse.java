package cl.nike.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponse {

    // modelo
    private BigDecimal idModelo;
    private String nombreModelo;
    private BigDecimal precio;

    // marca
    private BigDecimal idMarca;
    private String nombreMarca;

    // pais
    private BigDecimal idPais;
    private String nombrePais;

}