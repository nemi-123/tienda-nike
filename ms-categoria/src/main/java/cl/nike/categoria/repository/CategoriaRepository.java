package cl.nike.categoria.repository;

import cl.nike.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, BigDecimal> {
    // Hereda automáticamente: save(), findById(), findAll(), deleteById(), existsById()
}