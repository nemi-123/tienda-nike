package cl.nike.categoria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    // Producto
    private BigDecimal idProducto;
    private String nombreProducto;
    private BigDecimal precio;
}
