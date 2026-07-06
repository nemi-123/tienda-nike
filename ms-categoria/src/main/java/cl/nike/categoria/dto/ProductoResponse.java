package cl.nike.categoria.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductoResponse extends RepresentationModel<ProductoResponse>  {

    private BigDecimal idProducto;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal idCategoria;

    private List<CategoriaResponse> categorias; 
}