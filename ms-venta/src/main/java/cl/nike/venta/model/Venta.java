package cl.nike.venta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    private BigDecimal idventa;

    @Column(name = "fecha", nullable = true, length = 10)
    private String fecha;

    @Column(name = "total", nullable = true)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcliente", nullable = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Detallevta> detalles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(idventa, venta.idventa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idventa);
    }
}
