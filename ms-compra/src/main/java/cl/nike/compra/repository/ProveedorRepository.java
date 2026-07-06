package cl.nike.compra.repository;

import cl.nike.compra.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, BigDecimal> {
}