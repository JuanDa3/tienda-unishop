package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, String>{

    @Query("select p from Producto p where p.usuario.codigo = :codigo")
    List<Producto>listaProductos(int codigo);

    @Query("select u, p from Usuario u left join u.productoUsuarios p")
    List<Producto>listaProductosUsuarios();

    @Query("select c.nombre, s.codigo, count (p.nombre) from Producto p join p.subastas s join p.categorias c group by c.nombre")
    List<Object>listaCategorias();

    @Query("select c, max(dc.producto) from Compra c join c.detalleCompras dc join dc.producto p join p.categorias where c.codigo = :categoria")
    Optional<Producto>productoMasVendido(String categoria);

    @Query("select p.usuario.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(String codigo);

    //@Query("select c from Producto p join p.comentarios c where p.codigo = :id")
    //List<Comentario>obtenerComentarios(String codigo);

    @Query("select p.nombre, c from Producto p left join p.comentarios c")
    List<Object[]>listaProductosYComentarios();

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido (p.nombre, p.descripcion, p.precio, p.ciudad) from Producto p where  :fechaActual < p.fechaLimite")
    List<ProductoValido>listarProductosValidos(LocalDateTime fechaActual);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto>buscarProductoNombre(String nombre);

}
