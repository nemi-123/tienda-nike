package cl.nike.vendedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponse {

    // vendedor
    private BigDecimal idVendedor;
    private String nombreVendedor;
    private BigDecimal sueldo;

    // sucursal
    private BigDecimal idSucursal;
    private String nombreSucursal;

    // venta
    private BigDecimal idVenta;
    private String fecha;
    private BigDecimal total;

}