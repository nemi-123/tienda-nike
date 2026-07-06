package cl.nike.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.nike.categoria.model.Categoria;
import java.math.BigDecimal;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, BigDecimal> {
    // Puedes agregar métodos personalizados aquí si es necesario
}