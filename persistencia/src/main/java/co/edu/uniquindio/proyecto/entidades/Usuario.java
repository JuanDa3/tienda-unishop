package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    //Atributos propios de la entidad
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private List<String>telefonos;

    //Relaciones
    @Column(length = 40)
    private String username;

    @ManyToOne
    //@JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "usuarios")
    @ToString.Exclude
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<DetalleSubasta> detalleSubastas;

    //Constructor SuperClase
    public Usuario(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
