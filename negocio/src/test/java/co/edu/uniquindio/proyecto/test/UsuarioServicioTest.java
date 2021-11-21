package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest() {
        Usuario u = new Usuario("123", "juan", "juand.naranjos@uqvirtual.edu.co", "123");
        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
