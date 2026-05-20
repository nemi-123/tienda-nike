package cl.nike.zapatilla.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
    private BigDecimal idmodelo;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmarca", nullable = true)
    private Marca marca;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Zapatilla> zapatillas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modelo modelo = (Modelo) o;
        return Objects.equals(idmodelo, modelo.idmodelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmodelo);
    }
}
