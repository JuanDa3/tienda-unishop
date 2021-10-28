package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import co.edu.uniquindio.proyecto.repositorios.DetalleSubastaRepo;
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
public class DetalleSubastaTest {

    @Autowired
    private DetalleSubastaRepo detalleSubastaRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    @Test
    // registrat¿r un detalle subasta
    public void registrardetalleSubastaTest() {
        DetalleSubasta detalleSubasta = new DetalleSubasta("1", 20000, LocalDate.of(2018, 10, 30));
        DetalleSubasta detalleSubastaGuardado = detalleSubastaRepo.save(detalleSubasta);
        Assertions.assertNotNull(detalleSubastaGuardado);

    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void eliminardetalleSubastaTest() {
        detalleSubastaRepo.deleteById("1");

        DetalleSubasta detalleSubastaBuscado = detalleSubastaRepo.findById("1").orElse(null);

        Assertions.assertNull(detalleSubastaBuscado);

    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void actualizardetalleSubastaTest() {
        DetalleSubasta detalleSubastaGuardado = detalleSubastaRepo.findById("1").orElse(null);
        detalleSubastaGuardado.setValor(12000);
        detalleSubastaRepo.save(detalleSubastaGuardado);

        DetalleSubasta detalleSubastaBuscado = detalleSubastaRepo.findById("1").orElse(null);
        Assertions.assertEquals(12000, detalleSubastaBuscado.getValor());

    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listardetalleSubastaTest() {
        List<DetalleSubasta> detalleSubasta = detalleSubastaRepo.findAll();
        detalleSubasta.forEach(u -> System.out.println(u));
    }
}
