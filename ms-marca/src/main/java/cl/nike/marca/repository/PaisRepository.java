package cl.nike.marca.repository;

import cl.nike.marca.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface PaisRepository extends JpaRepository<Pais, BigDecimal> {
}