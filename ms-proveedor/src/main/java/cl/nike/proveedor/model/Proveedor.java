package cl.nike.proveedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @Column(name = "idproveedor", nullable = false)
    private BigDecimal idProveedor;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "telefono", nullable = true, length = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idciudad", nullable = true)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proveedor)) return false;
        Proveedor that = (Proveedor) o;
        return idProveedor != null && idProveedor.equals(that.idProveedor);
    }

    @Override
    public int hashCode() {
        return idProveedor != null ? idProveedor.hashCode() : 0;
    }
}
