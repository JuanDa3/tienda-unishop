package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Compra implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Column(nullable = false, name = "medio_pago")
    private MedioPago medioPago;

    //Relaciones
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    //Constructor Completo
    public Compra(String codigo, LocalDate fechaCompra, MedioPago medioPago)
    {
        this.codigo = codigo;
        this.fechaCompra = fechaCompra;
        this.medioPago = medioPago;
    }
}
