package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatTest {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarChatTest(){
        Producto producto = Producto.builder()
                .codigo("1").nombre("mouse gamer").unidades(5)
                .descripcion("mouse para juegos").precio(15000)
                .descuento(5)
                .fechaLimite(LocalDate.of(2021,10,20)).build();
        productoRepo.save(producto);

        Usuario newUsuario = new Usuario("4", "Jhan", "Jcmc@gmail.com", "100232");
        usuarioRepo.save(newUsuario);

        Chat chat = new Chat("1", newUsuario, producto);

        Chat chatGuardado = chatRepo.save(chat);
        Assertions.assertNotNull(chatGuardado);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void eliminarChatTest(){
        Producto producto = Producto.builder()
                .codigo("1").nombre("mouse gamer").unidades(5)
                .descripcion("mouse para juegos").precio(15000)
                .descuento(5)
                .fechaLimite(LocalDate.of(2021,10,20)).build();
        productoRepo.save(producto);

        Usuario newUsuario = new Usuario("4", "Jhan", "Jcmc@gmail.com", "100232");
        usuarioRepo.save(newUsuario);

        Chat chat = new Chat("1", newUsuario, producto);
        Chat chatGuardado = chatRepo.save(chat);
        chatRepo.deleteById("1");

        Chat chatBuscado = chatRepo.findById("1").orElse(null);
        Assertions.assertNull(chatBuscado);
    }

    @Test
    public void actualizarChatTest(){
        Producto producto = Producto.builder()
                .codigo("1").nombre("mouse gamer").unidades(5)
                .descripcion("mouse para juegos").precio(15000)
                .descuento(5)
                .fechaLimite(LocalDate.of(2021,10,20)).build();
        productoRepo.save(producto);

        Producto producto2 = Producto.builder()
                .codigo("2").nombre("teclado gamer").unidades(5)
                .descripcion("teclado para juegos").precio(35000)
                .descuento(10)
                .fechaLimite(LocalDate.of(2021,10,25)).build();
        productoRepo.save(producto);

        Usuario newUsuario = new Usuario("4", "Jhan", "Jcmc@gmail.com", "100232");
        usuarioRepo.save(newUsuario);

        Chat chat = new Chat("1", newUsuario, producto);
        Chat chatGuardado = chatRepo.save(chat);
        chatGuardado.setProducto(producto2);
        chatRepo.save(chatGuardado);

        Chat chatBuscado = chatRepo.findById("1").orElse(null);
        Assertions.assertEquals(producto2,chatBuscado.getProducto());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarChatTest(){

        List<Chat> chats = chatRepo.findAll();
        chats.forEach(c -> System.out.println(c));
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarChatVendedorTest(){
        List<Chat> chats = chatRepo.listaChatsVendedor("1");
        chats.forEach(c -> System.out.println(c));
    }
}
