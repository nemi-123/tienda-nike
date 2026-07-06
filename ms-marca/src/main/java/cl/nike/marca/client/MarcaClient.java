package cl.nike.marca.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-modelo")
public interface MarcaClient {

    @GetMapping("/api/marcas/{id}")
    Object findById(@PathVariable BigDecimal id);

}