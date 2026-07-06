package cl.nike.venta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallevtaRequest {

    @NotNull(message = "El id del detalle es obligatorio")
    private BigDecimal iddetalle;

    @NotNull(message = "La cantidad es obligatoria")
    @PositiveOrZero(message = "La cantidad debe ser mayor o igual a cero")
    private BigDecimal cantidad;

    @NotNull(message = "El subtotal es obligatorio")
    @PositiveOrZero(message = "El subtotal debe ser un valor positivo")
    private BigDecimal subtotal;

    @NotNull(message = "El id de la venta asociada es obligatorio")
    private BigDecimal idventa; // Relación directa por ID para el Request
}