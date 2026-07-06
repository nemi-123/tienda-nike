package cl.nike.compra.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallecmpRequest {

    @NotNull(message = "El id del detalle es obligatorio")
    private BigDecimal idDetalle;

    private BigDecimal cantidad;

    private BigDecimal precio;

    @NotNull(message = "El id de la compra es obligatorio")
    private BigDecimal idCompra;
}