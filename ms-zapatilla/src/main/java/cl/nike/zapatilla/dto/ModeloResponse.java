package cl.nike.zapatilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloResponse {

    private BigDecimal idmodelo;
    private String nombre;
    private BigDecimal idmarca; // ID de la marca asociada para identificarla fácilmente
}