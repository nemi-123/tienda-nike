package cl.nike.vendedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vendedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {

    @Id
    @Column(name = "idvendedor", nullable = false)
    private BigDecimal idVendedor;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "sueldo", nullable = true)
    private BigDecimal sueldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsucursal", nullable = true)
    private Sucursal sucursal;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendedor)) return false;
        Vendedor that = (Vendedor) o;
        return idVendedor != null && idVendedor.equals(that.idVendedor);
    }

    @Override
    public int hashCode() {
        return idVendedor != null ? idVendedor.hashCode() : 0;
    }
}
