package cl.nike.proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaProveedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaProveedorApplication.class, args);
    }

}