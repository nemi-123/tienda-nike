package cl.nike.marca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaMarcaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaMarcaApplication.class, args);
    }

}