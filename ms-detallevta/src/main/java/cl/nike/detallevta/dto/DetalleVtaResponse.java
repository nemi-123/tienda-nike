package cl.nike.detallevta.dto;

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
public class DetallevtaResponse extends RepresentationModel<DetallevtaResponse>  {

    private BigDecimal idDetalle;
    private BigDecimal cantidad;
    private BigDecimal subtotal;
    private BigDecimal idVenta;
    private BigDecimal idProducto;
}