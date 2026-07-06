package cl.nike.marca.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaisRequest {

    @NotNull(message = "El id del país es obligatorio")
    private BigDecimal idPais;

    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;
}   