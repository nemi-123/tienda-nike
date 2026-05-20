package cl.nike.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    // CIUDAD
    @NotNull(message = "El id de ciudad es obligatorio")
    private BigDecimal idCiudad;

    @NotBlank(message = "El nombre de ciudad es obligatorio")
    @Size(max = 50, message = "El nombre de ciudad no puede superar los 50 caracteres")
    private String nombreCiudad;

    // CLIENTE
    @NotNull(message = "El id del cliente es obligatorio")
    private BigDecimal idCliente;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 50, message = "El nombre del cliente no puede superar los 50 caracteres")
    private String nombreCliente;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    private String telefono;

    // COMPRA
    @NotNull(message = "El id de compra es obligatorio")
    private BigDecimal idCompra;

    @NotBlank(message = "La fecha es obligatoria")
    private String fecha;

    @NotNull(message = "El total es obligatorio")
    private BigDecimal total;
}