package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    @Query("select u from Usuario u where u.nombre = :nombre")
    List<Usuario>obtenerUsuariosPorNombre(String nombre);

    //inferencia de consultas
    List<Usuario>findAllByNombreContains(String nombre);
    Optional<Usuario>findByEmail(String email); //el optional sirve para que el retorno no sea null

    @Query("select u.productoUsuarios from Usuario u where u.codigo = :codigo")
    List<Usuario>listaFavoritos(String codigo);
}
