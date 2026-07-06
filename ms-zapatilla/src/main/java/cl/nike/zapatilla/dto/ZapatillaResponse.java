package cl.nike.zapatilla.dto;

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
public class ZapatillaResponse extends RepresentationModel<ZapatillaResponse>  {

    private BigDecimal idzapatilla;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal stock;
    private BigDecimal idmodelo; // Retornamos el ID para mantener la respuesta estructurada y plana
}