package cl.nike.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaVentaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaVentaApplication.class, args);
    }

}