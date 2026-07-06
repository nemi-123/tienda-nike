package cl.nike.detallevta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private BigDecimal idProducto;
    private String nombre;
    private BigDecimal precio;
}