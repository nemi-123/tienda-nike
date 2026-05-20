package cl.nike.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponse {

    // proveedor
    private BigDecimal idProveedor;
    private String nombreProveedor;
    private String telefono;

    // ciudad
    private BigDecimal idCiudad;
    private String nombreCiudad;

    // compra
    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;

}