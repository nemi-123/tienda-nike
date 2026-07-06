package cl.nike.detallevta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaDetallevtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaDetallevtaApplication.class, args);
    }

}