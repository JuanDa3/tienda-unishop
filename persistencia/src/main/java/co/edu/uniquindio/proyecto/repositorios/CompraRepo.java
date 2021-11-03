package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, String> {

    @Query("select c.medioPago, count(c.codigo) from Compra c join c.detalleCompras dc group by c.medioPago")
    List<Object[]>cantidadComprasMP();

    @Query("select c.codigo, sum(dc.unidades * dc.precioProducto) from DetalleCompra dc join dc.compra c join c.usuario u where u.codigo = :codigoUsuario group by c")
    List<Object>totalComprasUsuario(String codigoUsuario);
}
