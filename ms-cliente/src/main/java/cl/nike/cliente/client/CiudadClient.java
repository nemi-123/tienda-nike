package cl.nike.cliente.client;

import cl.nike.cliente.dto.CiudadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-compra")
public interface CiudadClient {

    @GetMapping("/api/ciudades/{id}")
    CiudadResponse obtenerCiudad(@PathVariable BigDecimal id);
}