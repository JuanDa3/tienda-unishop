package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{
    private final CategoriaRepo categoriaRepo;

    public CategoriaServicioImpl(CategoriaRepo categoriaRepo) {

        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria registrarCategoria(Categoria categoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(categoria.getCodigo());

        if (buscado.isPresent()) {
            throw new Exception("La categoria ya existe");
        }
        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(categoria.getCodigo());

        if (buscado.isEmpty()) {
            throw new Exception("La categoria no existe");
        }
        return categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String codigo) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(codigo);

        if (buscado.isEmpty()) {
            throw new Exception("La categoria no existe");
        }
        categoriaRepo.deleteById(codigo);
    }

    @Override
    public List<Categoria> listaCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(String id) {
        return categoriaRepo.getById(id);
    }
}
