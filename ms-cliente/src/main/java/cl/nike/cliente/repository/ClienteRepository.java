package cl.nike.cliente.repository;

import cl.nike.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, BigDecimal> {

    // AÑADE ESTA LÍNEA:
    Optional<Cliente> findByEmail(String email);
}