package cl.nike.vendedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {

    private BigDecimal idVenta;
    private String fecha;
    private BigDecimal total;
    private BigDecimal idVendedor; // Retornamos el ID plano para el JSON final
}