package cl.nike.cliente.repository;

import cl.nike.cliente.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, BigDecimal> {
}