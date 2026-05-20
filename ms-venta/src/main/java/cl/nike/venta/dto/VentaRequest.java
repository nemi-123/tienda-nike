package cl.nike.venta.dto;

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

    // venta
    @NotNull(message = "El id de la venta es obligatorio")
    private BigDecimal idVenta;

    @Size(max = 10, message = "La fecha no puede superar los 10 caracteres")
    private String fecha;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;

    // cliente
    @NotNull(message = "El id del cliente es obligatorio")
    private BigDecimal idCliente;

}