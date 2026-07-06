package cl.nike.zapatilla.dto;

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
public class ModeloRequest {

    @NotNull(message = "El id de modelo es obligatorio")
    private BigDecimal idmodelo;

    @NotBlank(message = "El nombre del modelo es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotNull(message = "El id de la marca asociada es obligatorio")
    private BigDecimal idmarca; // Usamos solo el ID de la marca para vincularlos
}