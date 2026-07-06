package cl.nike.modelo.repository;

import cl.nike.modelo.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, BigDecimal> {
}