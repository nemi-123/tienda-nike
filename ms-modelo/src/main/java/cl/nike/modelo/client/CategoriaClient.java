package cl.nike.modelo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-categoria")
public interface CategoriaClient {

    @GetMapping("/api/categorias/{id}")
    Object findById(@PathVariable BigDecimal id);

}