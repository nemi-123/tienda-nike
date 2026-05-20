package cl.nike.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {

    // venta
    private BigDecimal idVenta;
    private String fecha;
    private BigDecimal total;

    // cliente
    private BigDecimal idCliente;
    private String nombreCliente;

    // detalle venta
    private BigDecimal idDetalle;
    private BigDecimal cantidad;
    private BigDecimal subtotal;

}