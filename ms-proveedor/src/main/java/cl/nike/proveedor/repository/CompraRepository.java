package cl.nike.proveedor.repository;

import cl.nike.proveedor.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface CompraRepository extends JpaRepository<Compra, BigDecimal> {
}