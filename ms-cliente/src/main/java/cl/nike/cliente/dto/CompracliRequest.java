package cl.nike.cliente.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompracliRequest {

    @NotNull(message = "El id de la compra es obligatorio")
    private BigDecimal idCompra;

    @Size(max = 10, message = "La fecha no puede superar 10 caracteres")
    private String fecha;

    private BigDecimal total;

    @NotNull(message = "El id del cliente es obligatorio")
    private BigDecimal idCliente;
}