package cl.nike.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallevtaResponse {

    private BigDecimal iddetalle;
    private BigDecimal cantidad;
    private BigDecimal subtotal;
    private BigDecimal idventa; // Devolvemos el ID de la venta para que sea fácil de leer en el JSON
}