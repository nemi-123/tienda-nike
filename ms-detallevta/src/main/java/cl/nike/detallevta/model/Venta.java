package cl.nike.detallevta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @Column(name = "idventa", nullable = false)
    private BigDecimal idVenta;

    @Column(name = "fecha", nullable = true, length = 10)
    private String fecha;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Detallevta> detalles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta)) return false;
        Venta that = (Venta) o;
        return idVenta != null && idVenta.equals(that.idVenta);
    }

    @Override
    public int hashCode() {
        return idVenta != null ? idVenta.hashCode() : 0;
    }
}
