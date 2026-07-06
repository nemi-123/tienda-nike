package cl.nike.vendedor.repository;

import cl.nike.vendedor.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface VentaRepository extends JpaRepository<Venta, BigDecimal> {
}