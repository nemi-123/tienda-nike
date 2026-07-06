package cl.nike.venta.repository; // Ajustado al paquete correcto de tu módulo de ventas

import cl.nike.venta.model.Venta; // Corregido el import para que apunte a tu modelo real
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface VentaRepository extends JpaRepository<Venta, BigDecimal> {
    
    // Aquí puedes agregar métodos personalizados en el futuro si los necesitas, por ejemplo:
    // List<Venta> findByFecha(String fecha);
}