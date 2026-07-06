package cl.nike.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompracliResponse {

    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;
    private BigDecimal idCliente;
}