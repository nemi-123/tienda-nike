package cl.nike.vendedor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalResponse {

    private BigDecimal idSucursal;
    private String nombre;
}