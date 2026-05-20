package cl.nike.compra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponse {

    // compra
    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;

    // proveedor
    private BigDecimal idProveedor;
    private String nombreProveedor;
    private String telefono;

    // detalle compra
    private BigDecimal idDetalle;
    private BigDecimal cantidad;
    private BigDecimal precio;

}