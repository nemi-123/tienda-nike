package cl.nike.categoria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    // Categoria
    private BigDecimal idCategoria;
    private String nombreCategoria;

}