package cl.nike.modelo.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    private BigDecimal idCategoria;
    private String nombre;
}