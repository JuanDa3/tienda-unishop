package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

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

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarNombreTest(){
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Juan");
        lista.forEach(u -> System.out.println(u));
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);

        Page<Usuario>lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));

        List<Usuario>lista2 = usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void obtenerNombreVendedorTest(){
        String nombre = productoRepo.obtenerNombreVendedor("1");
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void obtenerFavoritosUsuario(){
        List<Producto>favoritos = usuarioRepo.obtenerProductosFavoritos("usuario@mail.com");
        Assertions.assertEquals(2,favoritos.size());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarUsuariosProductosTest(){
        List<UsuarioYProducto>respuesta = usuarioRepo.listarUsuariosYProductos();
        respuesta.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarUsuariosComentariosTest(){
        List<Usuario>usuarios = usuarioRepo.listarUsariosComentarios("1");
        usuarios.forEach(System.out::println);
    }

}


//expresiones lambda lista.forEach(System.out::println)