package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat,String> {

    @Query("select c from Chat c join c.usuario u join u.productos where u.codigo = :codigo")
    List<Chat>listaChatsVendedor(String codigo);
}
