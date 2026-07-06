package cl.nike.zapatilla.repository;

import cl.nike.zapatilla.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, BigDecimal> {
}