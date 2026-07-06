package cl.nike.modelo.repository;

import cl.nike.modelo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, BigDecimal> {
}