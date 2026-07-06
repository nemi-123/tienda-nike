package cl.nike.proveedor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-proveedor")
public interface CiudadClient {

    @GetMapping("/api/ciudades/{id}")
    Object findById(@PathVariable BigDecimal id);

}