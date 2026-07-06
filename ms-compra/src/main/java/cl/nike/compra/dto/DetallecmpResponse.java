package cl.nike.compra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallecmpResponse {

    private BigDecimal idDetalle;
    private BigDecimal cantidad;
    private BigDecimal precio;
    private BigDecimal idCompra;
}