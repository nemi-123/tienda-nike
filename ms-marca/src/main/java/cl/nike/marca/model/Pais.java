package cl.nike.marca.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pais {

    @Id
    @Column(name = "idpais", nullable = false)
    private BigDecimal idPais;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Marca> marcas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais that = (Pais) o;
        return idPais != null && idPais.equals(that.idPais);
    }

    @Override
    public int hashCode() {
        return idPais != null ? idPais.hashCode() : 0;
    }
}
