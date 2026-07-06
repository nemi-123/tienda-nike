package cl.nike.vendedor.dto;

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
public class VendedorRequest {

    @NotNull(message = "El id del vendedor es obligatorio")
    private BigDecimal idVendedor;

    @NotBlank(message = "El nombre del vendedor no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotNull(message = "El sueldo es obligatorio")
    @PositiveOrZero(message = "El sueldo debe ser un valor positivo o cero")
    private BigDecimal sueldo;

    @NotNull(message = "El id de la sucursal es obligatorio")
    private BigDecimal idSucursal; // Relación directa por ID para el Request
}