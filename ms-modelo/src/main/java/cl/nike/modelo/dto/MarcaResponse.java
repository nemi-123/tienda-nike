package cl.nike.modelo.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponse {

    private BigDecimal idMarca;
    private String nombre;
}