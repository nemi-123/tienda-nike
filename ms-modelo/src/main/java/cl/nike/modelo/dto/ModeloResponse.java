package cl.nike.modelo.dto;

import lombok.*;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModeloResponse extends RepresentationModel<ModeloResponse> {

    private BigDecimal idModelo;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal idMarca;
    private BigDecimal idCategoria;
}