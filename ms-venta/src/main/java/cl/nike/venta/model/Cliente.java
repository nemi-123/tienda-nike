package cl.nike.venta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(name = "idcliente", nullable = false)
    private BigDecimal idcliente;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idcliente, cliente.idcliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente);
    }
}
