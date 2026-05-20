package cl.nike.modelo.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "modelo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modelo {

    @Id
    @Column(name = "idmodelo", nullable = false)
    private BigDecimal idModelo;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "precio", nullable = true)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmarca", nullable = true)
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategoria", nullable = true)
    private Categoria categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modelo)) return false;
        Modelo that = (Modelo) o;
        return idModelo != null && idModelo.equals(that.idModelo);
    }

    @Override
    public int hashCode() {
        return idModelo != null ? idModelo.hashCode() : 0;
    }
}
