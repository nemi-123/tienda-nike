package cl.nike.cliente.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "credencial_cliente") // <-- Asegúrate de que este nombre sea EXACTO al de tu tabla en la BD
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredencialCliente {

    @Id
    @Column(name = "id_credencial", nullable = false) // <-- El nombre exacto de la columna PK en tu BD
    private BigDecimal idCredencial;

    // Agrega aquí los demás campos que tenga tu tabla en la base de datos
    // Por ejemplo:
    // @Column(name = "username", nullable = false, length = 100)
    // private String username;

    // Esta es la relación inversa que conecta con el 'mappedBy = "cliente"' de tu clase Cliente
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false) // <-- El nombre exacto de la columna FK en tu BD
    private Cliente cliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredencialCliente)) return false;
        CredencialCliente that = (CredencialCliente) o;
        return idCredencial != null && idCredencial.equals(that.idCredencial);
    }

    @Override
    public int hashCode() {
        return idCredencial != null ? idCredencial.hashCode() : 0;
    }
}