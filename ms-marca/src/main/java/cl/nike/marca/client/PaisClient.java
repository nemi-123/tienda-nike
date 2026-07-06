package cl.nike.marca.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-categoria")
public interface PaisClient {

    @GetMapping("/api/paises/{id}")
    Object findById(@PathVariable BigDecimal id);

}