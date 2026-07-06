package cl.nike.vendedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaVendedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaVendedorApplication.class, args);
    }

}