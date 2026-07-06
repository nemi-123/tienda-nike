package cl.nike.compra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponse {

    private BigDecimal idProveedor;
    private String nombre;
    private String telefono;
}