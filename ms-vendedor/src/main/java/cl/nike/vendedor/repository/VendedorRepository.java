package cl.nike.vendedor.repository;

import cl.nike.vendedor.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, BigDecimal> {
}