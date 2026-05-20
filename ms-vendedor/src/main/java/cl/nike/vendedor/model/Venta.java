package cl.nike.vendedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvendedor", nullable = true)
    private Vendedor vendedor;

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
