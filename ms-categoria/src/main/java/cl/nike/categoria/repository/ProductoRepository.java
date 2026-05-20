package cl.nike.categoria.repository;

import cl.nike.categoria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, BigDecimal> {
    // Listo con save(), findById(), etc.
}