package cl.nike.detallevta.dto;

import jakarta.validation.constraints.NotNull;
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
    private BigDecimal idVenta;

    @Size(max = 10, message = "La fecha no puede superar 10 caracteres")
    private String fecha;

    private BigDecimal total;
}