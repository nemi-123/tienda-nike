package cl.nike.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadResponse {

    private long idCiudad;
    private String nombre;
}