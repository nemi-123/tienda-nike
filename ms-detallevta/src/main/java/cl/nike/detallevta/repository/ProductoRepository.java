package cl.nike.detallevta.repository;

import cl.nike.detallevta.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, BigDecimal> {
}