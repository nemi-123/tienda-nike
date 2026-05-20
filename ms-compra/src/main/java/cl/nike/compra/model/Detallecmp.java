package cl.nike.compra.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detallecmp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detallecmp {

    @Id
    @Column(name = "id_detalle", nullable = false)
    private BigDecimal idDetalle;

    @Column(name = "cantidad", nullable = true)
    private BigDecimal cantidad;

    @Column(name = "precio", nullable = true)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", nullable = true)
    private Compra compra;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detallecmp)) return false;
        Detallecmp that = (Detallecmp) o;
        return idDetalle != null && idDetalle.equals(that.idDetalle);
    }

    @Override
    public int hashCode() {
        return idDetalle != null ? idDetalle.hashCode() : 0;
    }
}
