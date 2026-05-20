package cl.nike.categoria.repository;

import cl.nike.categoria.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, BigDecimal> {
}