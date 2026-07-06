package cl.nike.cliente.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(
    name = "cliente",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_cliente_email", columnNames = "email")
    },
    indexes = {
        @Index(name = "idx_cliente_activo", columnList = "activo")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(name = "id_cliente", nullable = false)
    // Nota: Si tu base de datos genera este ID automáticamente (ej. Secuencia o Identity), 
    // podrías agregar aquí @GeneratedValue(strategy = GenerationType.IDENTITY o SEQUENCE)
    private BigDecimal idCliente;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "telefono", nullable = true, length = 20)
    private String telefono;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "activo")
    private Boolean activo;

    // --- Relaciones existentes de tu modelo original ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", nullable = true)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compracli> compras;

    // --- Nuevas relaciones de credenciales (estilo Usuario) ---

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private CredencialCliente credencial;

    // --- Métodos Equals y HashCode de buenas prácticas para JPA ---

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
