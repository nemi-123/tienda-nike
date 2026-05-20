package cl.nike.detallevta.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVtaRequest {

    // DETALLE
    @NotNull(message = "El id del detalle es obligatorio")
    private BigDecimal idDetalle;

    @NotNull(message = "La cantidad es obligatoria")
    private BigDecimal cantidad;

    @NotNull(message = "El subtotal es obligatorio")
    private BigDecimal subtotal;

    // RELACIÓN VENTA
    @NotNull(message = "El id de la venta es obligatorio")
    private BigDecimal idVenta;

    // RELACIÓN PRODUCTO
    @NotNull(message = "El id del producto es obligatorio")
    private BigDecimal idProducto;
}