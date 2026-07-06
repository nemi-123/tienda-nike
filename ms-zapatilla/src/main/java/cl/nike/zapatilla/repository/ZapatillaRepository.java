package cl.nike.zapatilla.repository;

import cl.nike.zapatilla.model.Zapatilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface ZapatillaRepository extends JpaRepository<Zapatilla, BigDecimal> {
    // Hereda automáticamente findById(BigDecimal id) y existsById(BigDecimal id)
}