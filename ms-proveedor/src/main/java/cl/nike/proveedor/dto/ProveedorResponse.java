package cl.nike.proveedor.dto;

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
public class ProveedorResponse extends RepresentationModel<ProveedorResponse> {

    private BigDecimal idProveedor;
    private String nombre;
    private String telefono;
    private BigDecimal idCiudad; // Retornamos el ID plano para el JSON de salida
}