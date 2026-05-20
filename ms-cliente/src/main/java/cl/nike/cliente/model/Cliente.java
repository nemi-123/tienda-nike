package cl.nike.cliente.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(name = "id_cliente", nullable = false)
    private BigDecimal idCliente;

    @Column(name = "nombre", nullable = true, length = 50)
    private String nombre;

    @Column(name = "telefono", nullable = true, length = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", nullable = true)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compracli> compras;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente that = (Cliente) o;
        return idCliente != null && idCliente.equals(that.idCliente);
    }

    @Override
    public int hashCode() {
        return idCliente != null ? idCliente.hashCode() : 0;
    }
}
