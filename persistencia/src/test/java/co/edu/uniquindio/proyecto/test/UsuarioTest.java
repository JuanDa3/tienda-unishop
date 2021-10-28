package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    //Metodos CRUD Test
    //Test para registrar un usuario
    @Test
    public void registrarUsuarioTest() {
        Usuario newUsuario = new Usuario("4", "Jhan", "Jcmc@gmail.com", "100232");
        Usuario saveUsuario = usuarioRepo.save(newUsuario);

        Assertions.assertNotNull(saveUsuario);
    }

    //Test para eliminar un usuario
    @Test
    @Sql("classpath:dataSet.sql")
    public void eliminarUsuarioTest() {
        usuarioRepo.deleteById("1");
        Usuario usuarioBorrado = usuarioRepo.findById("1").orElse(null);

        Assertions.assertNull(usuarioBorrado);
    }

    //Test para actualizar un usuario
    @Test
    @Sql("classpath:dataSet.sql")
    public void actualizarUsuarioTest() {
        Usuario usuarioGuardado = usuarioRepo.findById("1").orElse(null);
        assert usuarioGuardado != null;
        usuarioGuardado.setNombre("Carlos");
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioUpdate = usuarioRepo.findById("1").orElse(null);
        assert usuarioUpdate != null;
        Assertions.assertEquals("Carlos", usuarioUpdate.getNombre());
    }

    //Test para listar usuarios
    @Test
    @Sql("classpath:dataSet.sql")
    public void listarUsuariosTest() {
        List<Usuario> listaUsuarios = usuarioRepo.findAll();
//      System.out.println(listaUsuarios);
        listaUsuarios.forEach(System.out::println);
    }
}
