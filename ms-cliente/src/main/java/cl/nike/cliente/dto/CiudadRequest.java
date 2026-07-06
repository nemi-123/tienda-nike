package cl.nike.cliente.dto;

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
public class CiudadRequest {

    @NotNull(message = "El id de la ciudad es obligatorio")
    private BigDecimal idCiudad;

    @NotBlank(message = "El nombre de la ciudad no puede estar vacío")
    @Size(max = 50, message = "El nombre de la ciudad no puede superar los 50 caracteres")
    private String nombre;
}