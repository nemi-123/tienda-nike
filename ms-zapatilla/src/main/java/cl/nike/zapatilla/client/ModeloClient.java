package cl.nike.zapatilla.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-modelo")
public interface ModeloClient {

    @GetMapping("/api/modelos/{id}")
    Object findById(@PathVariable BigDecimal id);

}