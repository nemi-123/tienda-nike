package cl.nike.proveedor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-cliente")
public interface CompraClient {

    @GetMapping("/api/compras/{id}")
    Object findById(@PathVariable BigDecimal id);

}