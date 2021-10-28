package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class DetalleCompra implements Serializable {

    //Atributos propios de la entidad
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 40)
    private String codigo;

    @Min(0)
    @Column(nullable = false)
    private int unidades;

    @Min(0)
    @Column(nullable = false, name = "precio_producto")
    private int precioProducto;

    //Relaciones
    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    //Constructor
    public DetalleCompra(String codigo, int unidades, int precioProducto) {
        this.codigo = codigo;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
    }
}
