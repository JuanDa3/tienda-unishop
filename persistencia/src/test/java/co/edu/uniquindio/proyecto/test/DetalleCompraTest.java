package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    @Test
    //registrar un mensaje
    public void registrardetalleCompraTest() {
        DetalleCompra detalleCompra = new DetalleCompra("1", 2, 20000);
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.save(detalleCompra);
        Assertions.assertNotNull(detalleCompraGuardado);

    }

    @Test
    @Sql("classpath:dataSet.sql")
    //Eliminar un detalle compra
    public void eliminardetalleCompraTest() {

        detalleCompraRepo.deleteById("1");
        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById("1").orElse(null);

        Assertions.assertNull(detalleCompraBuscado);

    }

    @Test
    //Actualizar un detalle compra
    @Sql("classpath:dataSet.sql")
    public void actualizardetalleCompraTest() {
        DetalleCompra detalleCompraGuardado = detalleCompraRepo.findById("1").orElse(null);
        detalleCompraGuardado.setPrecioProducto(30000);
        detalleCompraRepo.save(detalleCompraGuardado);

        DetalleCompra detalleCompraBuscado = detalleCompraRepo.findById("1").orElse(null);
        Assertions.assertEquals(30000, detalleCompraBuscado.getPrecioProducto());


    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listardetalleCompraTest() {
        List<DetalleCompra> detalleCompras = detalleCompraRepo.findAll();
        detalleCompras.forEach(u -> System.out.println(u));

    }
}
