package cl.nike.modelo.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @Column(name = "idcategoria", nullable = false)
    private BigDecimal idCategoria;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Modelo> modelos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria that = (Categoria) o;
        return idCategoria != null && idCategoria.equals(that.idCategoria);
    }

    @Override
    public int hashCode() {
        return idCategoria != null ? idCategoria.hashCode() : 0;
    }
}
