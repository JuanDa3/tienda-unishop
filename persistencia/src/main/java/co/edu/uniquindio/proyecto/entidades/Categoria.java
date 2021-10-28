package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Categoria implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Column(length = 40, nullable = false)
    private String nombre;

    //Relaciones
    @ManyToMany(mappedBy = "categorias")
    @ToString.Exclude
    private List<Producto> productos;

    public Categoria(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
