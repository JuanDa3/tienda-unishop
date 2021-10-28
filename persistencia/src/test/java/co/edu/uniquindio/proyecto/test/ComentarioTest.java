package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {
    @Autowired
    private ComentarioRepo miCtRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    @Test
    public void registrarComentarioTest() {
        Comentario miCt = new Comentario("0", "Mensaje Prueba", "Respuesta Prueba", LocalDate.of(2018, 10, 30), 5);
        Comentario miCtGuardado = miCtRepo.save(miCt);
        Assertions.assertNotNull(miCtGuardado);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Eliminar un comentario
    public void eliminarComentarioTest() {
        miCtRepo.deleteById("1");
        Comentario miCt = miCtRepo.findById("1").orElse(null);

        Assertions.assertNull(miCt);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Actualizar Comentario
    public void actualizarComentarioTest() {
        Comentario miCt = miCtRepo.findById("1").orElse(null);
        miCt.setCalificacion(1);
        Comentario miCNuevo = miCtRepo.save(miCt);
        Assertions.assertEquals(1, miCNuevo.getCalificacion());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Mostrar lista de comentarios de un usuario
    public void listarComentariosTest() {
        List<Comentario> listaComentario = miCtRepo.findAll();

        Assertions.assertEquals(3, listaComentario.size());
    }
}
