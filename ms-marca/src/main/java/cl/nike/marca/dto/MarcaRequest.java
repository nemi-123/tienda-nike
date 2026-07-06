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
public class MarcaRequest {

    @NotNull(message = "El id de la marca es obligatorio")
    private BigDecimal idMarca;

    @Size(max = 50, message = "El nombre no puede superar 50 caracteres")
    private String nombre;

    @NotNull(message = "El id del país es obligatorio")
    private BigDecimal idPais;
}