package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    //@Length(max = 40)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    @Email(message = "Escriba un email valido")
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
