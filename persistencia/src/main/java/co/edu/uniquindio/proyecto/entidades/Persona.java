package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
@ToString
public class Persona implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Column(length = 40, nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    //Constructor
    public Persona(String codigo, String nombre, String email, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
}
