package cl.nike.venta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequest {

    @NotNull(message = "El id de la venta es obligatorio")
    private BigDecimal idventa;

    @NotBlank(message = "La fecha es obligatoria")
    @Size(max = 10, message = "La fecha no puede superar los 10 caracteres")
    private String fecha;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total debe ser un valor positivo o cero")
    private BigDecimal total;

    @NotNull(message = "El id del cliente es obligatorio")
    private BigDecimal idcliente; // Relación mapeada de forma directa por ID
}