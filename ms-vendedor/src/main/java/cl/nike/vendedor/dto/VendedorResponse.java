package cl.nike.vendedor.dto;

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
public class VendedorResponse extends RepresentationModel<VendedorResponse> {

    private BigDecimal idVendedor;
    private String nombre;
    private BigDecimal sueldo;
    private BigDecimal idSucursal; // Retornamos el ID plano para el JSON de respuesta
}