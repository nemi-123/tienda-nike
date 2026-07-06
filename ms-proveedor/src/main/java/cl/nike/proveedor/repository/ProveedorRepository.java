package cl.nike.proveedor.repository;

import cl.nike.proveedor.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, BigDecimal> {
}