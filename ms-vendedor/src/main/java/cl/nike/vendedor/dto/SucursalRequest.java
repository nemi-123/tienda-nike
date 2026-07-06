package cl.nike.vendedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalRequest {

    @NotNull(message = "El id de la sucursal es obligatorio")
    private BigDecimal idSucursal;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    @Size(max = 50, message = "El nombre de la sucursal no puede superar los 50 caracteres")
    private String nombre;
}