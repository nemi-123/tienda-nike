package cl.nike.modelo.repository;

import cl.nike.modelo.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, BigDecimal> {
}