package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Subasta implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    //Relaciones
    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<DetalleSubasta> detalleSubastas;

    //Constructor
    public Subasta(String codigo, LocalDate fechaLimite) {
        this.codigo = codigo;
        this.fechaLimite = fechaLimite;
    }
}
