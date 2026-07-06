package cl.nike.cliente.repository;

import cl.nike.cliente.model.Compracli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface CompracliRepository extends JpaRepository<Compracli, BigDecimal> {
}