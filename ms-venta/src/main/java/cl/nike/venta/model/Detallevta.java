package cl.nike.venta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

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
    private BigDecimal iddetalle;

    @Column(name = "cantidad", nullable = true)
    private BigDecimal cantidad;

    @Column(name = "subtotal", nullable = true)
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idventa", nullable = true)
    private Venta venta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detallevta that = (Detallevta) o;
        return Objects.equals(iddetalle, that.iddetalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddetalle);
    }
}
