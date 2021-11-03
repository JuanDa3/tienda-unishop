package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario,String>
{
    @Query("select c from Comentario c where c.producto.codigo = :codigoProducto")
    List<Comentario>listaComentarios(String codigoProducto);

    //Lista de comentarios que no ha respondido un usuario(vendedor)que tiene productos creados
    @Query("select c from  Comentario c where c.producto.usuario.codigo = :codigoUsuario and c.respuesta is null")
    List<Comentario>listaComenNoResp();

}
