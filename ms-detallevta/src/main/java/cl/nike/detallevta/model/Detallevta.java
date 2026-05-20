package cl.nike.detallevta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detallevta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detallevta {

    @Id
    @Column(name = "iddetalle", nullable = false)
    private BigDecimal idDetalle;

    @Column(name = "cantidad", nullable = true)
    private BigDecimal cantidad;

    @Column(name = "subtotal", nullable = true)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idventa", nullable = true)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto", nullable = true)
    private Producto producto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detallevta)) return false;
        Detallevta that = (Detallevta) o;
        return idDetalle != null && idDetalle.equals(that.idDetalle);
    }

    @Override
    public int hashCode() {
        return idDetalle != null ? idDetalle.hashCode() : 0;
    }
}
