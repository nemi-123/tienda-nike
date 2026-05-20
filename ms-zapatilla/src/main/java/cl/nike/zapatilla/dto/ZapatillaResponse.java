package cl.nike.zapatilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZapatillaResponse {

    // zapatilla
    private BigDecimal idZapatilla;
    private String nombreZapatilla;
    private BigDecimal precio;
    private BigDecimal stock;

    // modelo
    private BigDecimal idModelo;
    private String nombreModelo;

    // marca
    private BigDecimal idMarca;
    private String nombreMarca;

}