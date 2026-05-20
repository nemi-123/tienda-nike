package cl.nike.compra.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequest {

    // compra
    @NotNull(message = "El id de la compra es obligatorio")
    private BigDecimal idCompra;

    @Size(max = 10, message = "La fecha no puede superar los 10 caracteres")
    private String fecha;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;

    // proveedor
    @NotNull(message = "El id del proveedor es obligatorio")
    private BigDecimal idProveedor;

}