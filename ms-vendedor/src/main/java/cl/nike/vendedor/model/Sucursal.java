package cl.nike.vendedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "sucursal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sucursal {

    @Id
    @Column(name = "idsucursal", nullable = false)
    private BigDecimal idSucursal;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vendedor> vendedores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sucursal)) return false;
        Sucursal that = (Sucursal) o;
        return idSucursal != null && idSucursal.equals(that.idSucursal);
    }

    @Override
    public int hashCode() {
        return idSucursal != null ? idSucursal.hashCode() : 0;
    }
}
