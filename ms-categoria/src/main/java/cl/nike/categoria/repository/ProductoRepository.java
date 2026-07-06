package cl.nike.categoria.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.nike.categoria.model.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, BigDecimal> {
    // Puedes añadir métodos como existsByNombre(String nombre) si lo requieres
}