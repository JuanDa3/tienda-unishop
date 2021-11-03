package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubastaRepo extends JpaRepository<Subasta, String> {
    @Query("select u.nombre, max(ds.valor) from DetalleSubasta ds join ds.usuario u where ds.subasta.codigo = :codigo")
    String obtenerUsuarioGanador(String codigo);
}
