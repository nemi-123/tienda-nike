package cl.nike.proveedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadResponse {

    private BigDecimal idCiudad;
    private String nombreCiudad;
}