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
public class Chat implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    //Relaciones
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> mensajes;

    public Chat(String codigo, Usuario usuario, Producto producto) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.producto = producto;
    }
}
