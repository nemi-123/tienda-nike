package cl.nike.zapatilla.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    private BigDecimal idmarca;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Modelo> modelos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(idmarca, marca.idmarca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmarca);
    }
}
