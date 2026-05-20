package cl.nike.proveedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @Column(name = "idcompra", nullable = false)
    private BigDecimal idCompra;

    @Column(name = "fecha", nullable = true, length = 10)
    private String fecha;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproveedor", nullable = true)
    private Proveedor proveedor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra that = (Compra) o;
        return idCompra != null && idCompra.equals(that.idCompra);
    }

    @Override
    public int hashCode() {
        return idCompra != null ? idCompra.hashCode() : 0;
    }
}
