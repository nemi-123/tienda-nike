package cl.nike.proveedor.repository;

import cl.nike.proveedor.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, BigDecimal> {
}