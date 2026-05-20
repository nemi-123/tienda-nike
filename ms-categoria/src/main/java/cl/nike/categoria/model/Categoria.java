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
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @Column(name = "id_categoria", nullable = false)
    private BigDecimal idCategoria;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
