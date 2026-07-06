package cl.nike.compra.repository;

import cl.nike.compra.model.Detallecmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface DetallecmpRepository extends JpaRepository<Detallecmp, BigDecimal> {
}