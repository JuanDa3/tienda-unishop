package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo){
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El código no existe");
        }
        productoRepo.deleteById(codigo);//tener en cuenta que cuando se elimine revisar que registros dependen de otros
        //cascade delete hibernate
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigo).orElseThrow(() -> new ProductoNoEncontradoException("El producto no fue encontrado"));
    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDate.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return null;
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public List<Producto> listarPorCategoria(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {
        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductos(String codigoUsuario) throws Exception {
        return null;
    }

    @Override
    public List<CategoriaEnum> listarCategorias() {
        return Arrays.asList(CategoriaEnum.values());
    }

    @Override
    public CategoriaEnum obtenerCategoria(String categoria) throws Exception {
        return CategoriaEnum.valueOf(categoria);
    }
}
