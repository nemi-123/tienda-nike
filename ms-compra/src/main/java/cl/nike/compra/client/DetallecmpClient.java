package cl.nike.compra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "ms-detallevta")
public interface DetallecmpClient {

    @GetMapping("/api/detalles/{id}")
    Object findById(@PathVariable BigDecimal id);

}