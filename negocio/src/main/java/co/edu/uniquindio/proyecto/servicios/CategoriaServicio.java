package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {
    Categoria registrarCategoria(Categoria categoria) throws Exception;

    Categoria actualizarCategoria(Categoria categoria) throws Exception;

    void eliminarCategoria(String codigo) throws Exception;

    List<Categoria> listaCategorias();

    Categoria obtenerCategoria(String id);
}
