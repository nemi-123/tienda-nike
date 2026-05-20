package cl.nike.cliente.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "compracli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compracli {

    @Id
    @Column(name = "id_compra", nullable = false)
    private BigDecimal idCompra;

    @Column(name = "fecha", nullable = true, length = 10)
    private String fecha;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compracli)) return false;
        Compracli that = (Compracli) o;
        return idCompra != null && idCompra.equals(that.idCompra);
    }

    @Override
    public int hashCode() {
        return idCompra != null ? idCompra.hashCode() : 0;
    }
}
