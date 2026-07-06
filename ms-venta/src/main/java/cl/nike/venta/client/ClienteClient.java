package cl.nike.venta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-cliente")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    Object findById(@PathVariable BigDecimal id);

}