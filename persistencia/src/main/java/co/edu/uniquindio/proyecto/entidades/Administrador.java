package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class Administrador extends Persona{

    //Constructor SuperClase
    @Builder
    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
