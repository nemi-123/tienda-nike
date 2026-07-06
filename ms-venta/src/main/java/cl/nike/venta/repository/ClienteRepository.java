package cl.nike.venta.repository;

import cl.nike.venta.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, BigDecimal> {
}