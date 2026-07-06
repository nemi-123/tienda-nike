package cl.nike.compra.dto;

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
public class CompraResponse extends RepresentationModel<CompraResponse> {

    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;
    private BigDecimal idProveedor;
}