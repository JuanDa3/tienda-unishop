package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Column(length = 255, nullable = false)
    private String mensaje;

    @Column(length = 255, nullable = false)
    private String respuesta;

    @Column(name = "fecha_comentario", nullable = false)
    private LocalDate fechaComentario;

    @Min(0) @Max(5)
    @Column(nullable = false)
    private int calificacion;

    //Relaciones
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    //Constructor Completo
    public Comentario(String codigo, String mensaje, String respuesta, LocalDate fechaComentario, int calificacion)
    {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
    }

}
