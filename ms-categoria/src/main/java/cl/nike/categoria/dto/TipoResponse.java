package cl.nike.categoria.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoResponse {

    private BigDecimal idTipo;
    private String nombre;
}