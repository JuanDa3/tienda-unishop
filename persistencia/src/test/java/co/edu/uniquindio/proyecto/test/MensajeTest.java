package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
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
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    @Test
    //registrar un mensaje
    public void registrarMensajeTest() {
        Mensaje mensaje = new Mensaje("1", "mensaje", "emisor", LocalDate.of(2018, 10, 30));
        Mensaje mensajeGuardado = mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);

    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Elinar un mensaje
    public void eliminarMensajeTest() {

        mensajeRepo.deleteById("1");

        Mensaje mensajeBuscado = mensajeRepo.findById("1").orElse(null);

        Assertions.assertNull(mensajeBuscado);

    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Actualizar un mensaje
    public void actualizarMensajeTest() {

        Mensaje mensajeGuardado = mensajeRepo.findById("1").orElse(null);
        mensajeGuardado.setMensaje("Mensaje nuevo");
        mensajeRepo.save(mensajeGuardado);

        Mensaje mensajeBuscado = mensajeRepo.findById("1").orElse(null);
        Assertions.assertEquals("Mensaje nuevo", mensajeBuscado.getMensaje());

    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Mostrar lista de mensajes de un usuario
    public void listarMensajeTest() {

        List<Mensaje> mensajes = mensajeRepo.findAll();
        mensajes.forEach(u -> System.out.println(u));

    }
}
