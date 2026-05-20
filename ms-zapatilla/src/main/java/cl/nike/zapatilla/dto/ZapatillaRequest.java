package cl.nike.zapatilla.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZapatillaRequest {

    // zapatilla
    @NotNull(message = "El id de la zapatilla es obligatorio")
    private BigDecimal idZapatilla;

    @Size(max = 50, message = "El nombre de la zapatilla no puede superar los 50 caracteres")
    private String nombreZapatilla;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    private BigDecimal stock;

    // modelo
    @NotNull(message = "El id del modelo es obligatorio")
    private BigDecimal idModelo;

}