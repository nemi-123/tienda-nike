package cl.nike.categoria.dto;

import lombok.*;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriaResponse extends RepresentationModel<CategoriaResponse> {

    private BigDecimal idCategoria;
    private String nombre;
    private BigDecimal idTipo;

    
}