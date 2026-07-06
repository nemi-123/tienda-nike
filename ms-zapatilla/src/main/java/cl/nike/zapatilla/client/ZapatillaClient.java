package cl.nike.zapatilla.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-compra")
public interface ZapatillaClient {

    @GetMapping("/api/zapatillas/{id}")
    Object findById(@PathVariable BigDecimal id);

}