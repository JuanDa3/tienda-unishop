package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Comentario implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 255, nullable = false)
    private String mensaje;

    @Column(length = 255)
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

    public String getFechaEstilo(){
        return fechaComentario.format(DateTimeFormatter.ISO_DATE);
    }

    //Constructor Completo
    public Comentario(Integer codigo, String mensaje, String respuesta, LocalDate fechaComentario, int calificacion)
    {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
    }

}
