package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
