package cl.nike.vendedor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorRequest {

    // vendedor
    @NotNull(message = "El id del vendedor es obligatorio")
    private BigDecimal idVendedor;

    @Size(max = 50, message = "El nombre del vendedor no puede superar los 50 caracteres")
    private String nombreVendedor;

    @NotNull(message = "El sueldo es obligatorio")
    private BigDecimal sueldo;

    // sucursal
    @NotNull(message = "El id de la sucursal es obligatorio")
    private BigDecimal idSucursal;

}