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
public class CategoriaRequest {

    // Categoria
    @NotNull(message = "El id de categoría es obligatorio")
    private BigDecimal idCategoria;

    @NotBlank(message = "El nombre de categoría es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombreCategoria;
}