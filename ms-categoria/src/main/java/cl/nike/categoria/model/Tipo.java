package cl.nike.categoria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tipo {

    @Id
    @Column(name = "id_tipo", nullable = false)
    private BigDecimal idTipo;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    private List<Categoria> categorias;
}
