package cl.nike.proveedor.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ciudad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad {

    @Id
    @Column(name = "idciudad", nullable = false)
    private BigDecimal idCiudad;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Proveedor> proveedores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ciudad)) return false;
        Ciudad that = (Ciudad) o;
        return idCiudad != null && idCiudad.equals(that.idCiudad);
    }

    @Override
    public int hashCode() {
        return idCiudad != null ? idCiudad.hashCode() : 0;
    }
}
