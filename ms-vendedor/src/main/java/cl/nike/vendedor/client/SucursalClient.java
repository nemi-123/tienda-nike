package cl.nike.vendedor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-compra")
public interface SucursalClient {

    @GetMapping("/api/sucursales/{id}")
    Object findById(@PathVariable BigDecimal id);

}