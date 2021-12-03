package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException;

    List<Producto>listarProductos(Categoria categoria);

    List<Producto>listarTodosProductos();

    List<Producto>listarPorCategoria(Categoria categoria);

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto>buscarProductos(String nombreProducto, String[] filtros);

    List<Producto>listarProductos(String codigoUsuario) throws Exception;

    List<CategoriaEnum>listarCategorias();

    CategoriaEnum obtenerCategoria(String categoria) throws Exception;

    //El enum se hizo para seguir el ejemplo del profesor, se debe implementar igual que como se hizo con Ciudad
}
