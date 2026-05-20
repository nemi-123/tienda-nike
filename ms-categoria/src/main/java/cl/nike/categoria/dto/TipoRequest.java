package cl.nike.categoria.dto;

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
public class TipoRequest {
        // Tipo
    @NotNull(message = "El id del tipo es obligatorio")
    private BigDecimal idTipo;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombreTipo;



}
