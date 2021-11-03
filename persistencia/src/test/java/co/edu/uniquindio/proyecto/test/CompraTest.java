package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
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
public class CompraTest {
    @Autowired
    private CompraRepo miCpRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    @Test
    public void registrarCompraTest() {
        Compra miCp = new Compra("0", LocalDate.of(2018, 10, 30), MedioPago.TARJETA);
        Compra miCpGuardado = miCpRepo.save(miCp);
        Assertions.assertNotNull(miCpGuardado);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Eliminar una compra
    public void eliminarCompraTest() {
        miCpRepo.deleteById("1");
        Compra miCp = miCpRepo.findById("1").orElse(null);

        Assertions.assertNull(miCp);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Actualizar una compra
    public void actualizarCompraTest() {
        Compra miCp = miCpRepo.findById("1").orElse(null);
        miCp.setMedioPago(MedioPago.DECONTADO);
        Compra miCNuevo = miCpRepo.save(miCp);

        Assertions.assertEquals(MedioPago.DECONTADO, miCNuevo.getMedioPago());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Mostrar lista de compras de un usuario
    public void listarComprasTest() {
        List<Compra> listaCompras = miCpRepo.findAll();

        Assertions.assertEquals(3, listaCompras.size());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Mostrar lista de compras de un usuario
    public void listarComprasPorMP() {
        List<Object[]>listaComprasMP = miCpRepo.cantidadComprasMP();

        for(Object[] objeto: listaComprasMP){
            System.out.println(objeto[0]+"..."+objeto[1]);
        }
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void valorTotalComprasUsuarioTest(){
        List<Object>comprasUsuario = miCpRepo.totalComprasUsuario("1");
    }
}
