package cl.nike.compra.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @Column(name = "id_compra", nullable = false)
    private BigDecimal idCompra;

    @Column(name = "fecha", nullable = true, length = 10)
    private String fecha;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = true)
    private Proveedor proveedor;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Detallecmp> detalles;

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
