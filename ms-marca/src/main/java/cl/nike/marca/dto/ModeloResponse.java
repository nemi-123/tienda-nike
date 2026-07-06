package cl.nike.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloResponse {

    private BigDecimal idModelo;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal idMarca;
}