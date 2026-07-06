package cl.nike.zapatilla.repository;

import cl.nike.zapatilla.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, BigDecimal> {
    // Al heredar de JpaRepository, ya tienes listos todos los métodos CRUD básicos.
}
