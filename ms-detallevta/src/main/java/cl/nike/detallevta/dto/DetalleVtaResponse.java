package cl.nike.detallevta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVtaResponse {

    // detalleVta
    private BigDecimal idDetalle;
    private BigDecimal cantidad;
    private BigDecimal subtotal;
    //producto
    private BigDecimal idProducto;
    private String nombre;
    private BigDecimal precio;
    //veta
    private BigDecimal idVenta;
    private String fecha;
    private BigDecimal total;
}