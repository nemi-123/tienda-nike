package cl.nike.marca.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marca {

    @Id
    @Column(name = "idmarca", nullable = false)
    private BigDecimal idMarca;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpais", nullable = true)
    private Pais pais;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Modelo> modelos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marca)) return false;
        Marca that = (Marca) o;
        return idMarca != null && idMarca.equals(that.idMarca);
    }

    @Override
    public int hashCode() {
        return idMarca != null ? idMarca.hashCode() : 0;
    }
}
