package cl.nike.cliente.dto;

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
public class ClienteResponse  extends RepresentationModel<ClienteResponse> {

    private BigDecimal idCliente;
    private String nombre;
    private String telefono;
    private BigDecimal idCiudad;
}