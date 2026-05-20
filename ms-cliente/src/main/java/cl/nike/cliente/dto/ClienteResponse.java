package cl.nike.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {

    // Ciudad
    private BigDecimal idCiudad;
    private String nombreCiudad;

    // Cliente
    private BigDecimal idCliente;
    private String nombreCliente;
    private String telefono;

    // Compra
    private BigDecimal idCompra;
    private String fecha;
    private BigDecimal total;
}