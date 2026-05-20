package cl.nike.detallevta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @Column(name = "idproducto", nullable = false)
    private BigDecimal idProducto;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "precio", nullable = true)
    private BigDecimal precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Detallevta> detalles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto that = (Producto) o;
        return idProducto != null && idProducto.equals(that.idProducto);
    }

    @Override
    public int hashCode() {
        return idProducto != null ? idProducto.hashCode() : 0;
    }
}
