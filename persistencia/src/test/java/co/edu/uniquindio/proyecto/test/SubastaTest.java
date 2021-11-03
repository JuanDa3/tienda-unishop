package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
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
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;

    //Metodos CRUD Test
    //Test para registrar un subasta
    @Test
    public void registrarSubastaTest() {
        Subasta newSubasta = new Subasta("1", LocalDate.of(2018, 10, 30));
        Subasta saveSubasta = subastaRepo.save(newSubasta);

        Assertions.assertNotNull(saveSubasta);
    }

    //Test para eliminar una subasta
    @Test
    @Sql("classpath:dataSet.sql")
    public void eliminarSubastaTest() {
        subastaRepo.deleteById("2");
        Subasta subastaBorrada = subastaRepo.findById("2").orElse(null);

        Assertions.assertNull(subastaBorrada);
    }

    //Test para actualizar una subasta
    @Test
    @Sql("classpath:dataSet.sql")
    public void actualizarSubastaTest() {
        Subasta subastaGuardada = subastaRepo.findById("1").orElse(null);
        assert subastaGuardada != null;
        subastaGuardada.setFechaLimite(LocalDate.of(2021, 10, 20));
        subastaRepo.save(subastaGuardada);

        Subasta subastaUpdate = subastaRepo.findById("1").orElse(null);
        assert subastaUpdate != null;
        Assertions.assertEquals(LocalDate.of(2021, 10, 20),subastaUpdate.getFechaLimite());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarSubastaTest() {
        List<Subasta> listaSubastas = subastaRepo.findAll();
        listaSubastas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void usuarioGanadorTest() {
        String ganador = subastaRepo.obtenerUsuarioGanador("1");
        System.out.println(ganador);
    }
}
