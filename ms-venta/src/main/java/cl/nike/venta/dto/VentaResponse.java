package cl.nike.venta.dto;

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
public class VentaResponse extends RepresentationModel<VentaResponse>  {

    private BigDecimal idventa;
    private String fecha;
    private BigDecimal total;
    private BigDecimal idcliente; // Retornamos solo el ID del cliente para el JSON
}