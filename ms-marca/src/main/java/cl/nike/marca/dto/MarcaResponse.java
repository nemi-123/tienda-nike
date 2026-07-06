package cl.nike.marca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MarcaResponse extends RepresentationModel<MarcaResponse> {

    private BigDecimal idMarca;
    private String nombre;
    private BigDecimal idPais;
}