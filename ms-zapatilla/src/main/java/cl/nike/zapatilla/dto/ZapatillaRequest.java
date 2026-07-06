package cl.nike.zapatilla.dto;

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
public class ZapatillaRequest {

    @NotNull(message = "El id de la zapatilla es obligatorio")
    private BigDecimal idzapatilla;

    @NotBlank(message = "El nombre de la zapatilla es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser un valor positivo o cero")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock debe ser un valor positivo o cero")
    private BigDecimal stock;

    @NotNull(message = "El id del modelo asociado es obligatorio")
    private BigDecimal idmodelo; // Relación directa por ID
}