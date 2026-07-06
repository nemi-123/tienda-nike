package cl.nike.zapatilla.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "zapatilla")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zapatilla {

    @Id
    @Column(name = "idzapatilla")
    private BigDecimal idzapatilla;
    
    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "precio", nullable = true)
    private BigDecimal precio;

    @Column(name = "stock", nullable = true)
    private BigDecimal stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmodelo", nullable = true)
    private Modelo modelo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zapatilla zapatilla = (Zapatilla) o;
        return Objects.equals(idzapatilla, zapatilla.idzapatilla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idzapatilla);
    }
}
