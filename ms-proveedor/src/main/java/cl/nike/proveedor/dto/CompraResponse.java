package cl.nike.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponse {

    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;
    private BigDecimal idProveedor; // Mapeado plano para evitar ciclos pesados en JSON
}