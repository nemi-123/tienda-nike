package cl.nike.detallevta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallevtaRequest {

    @NotNull(message = "El id del detalle es obligatorio")
    private BigDecimal idDetalle;

    private BigDecimal cantidad;

    private BigDecimal subtotal;

    @NotNull(message = "El id de la venta es obligatorio")
    private BigDecimal idVenta;

    @NotNull(message = "El id del producto es obligatorio")
    private BigDecimal idProducto;
}