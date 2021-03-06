package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    //----------------------------------Metodos CRUD Test----------------------------------------
    //Test para registrar un producto
    @Test
    public void regsitrarProductoTest(){
        Producto newProducto = new Producto("Play Station 6", 5, "Es la mas nueva", 10000, 0, LocalDate.of(2021, 10, 20));
        Producto saveProducto = productoRepo.save(newProducto);

        Assertions.assertNotNull(saveProducto);
    }

    //Test para eliminar un producto
    @Test
    @Sql("classpath:dataSet.sql")
    public void eliminarProductoTest(){
        productoRepo.deleteById(2);
        Producto productoBorrado = productoRepo.findById(2).orElse(null);

        Assertions.assertNull(productoBorrado);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void actualizarProductoTest(){
        Producto productoGuardado = productoRepo.findById(1).orElse(null);
        assert productoGuardado != null;
        productoGuardado.setDescuento(0);

        Producto productoUpdate = productoRepo.findById(1).orElse(null);
        assert productoUpdate != null;
        Assertions.assertEquals(0,productoUpdate.getDescuento());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarProductoTest(){
        List<Producto> listaProductos = productoRepo.findAll();
        listaProductos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void cantProdCategoriaTest(){
        List<Object>listaProdCat = productoRepo.listaCategorias();
        listaProdCat.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void productoMasVendido(){
        Optional<Producto> producto = productoRepo.productoMasVendido("electodomestico");
        System.out.println(producto);
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarUsuariosProductosTest(){
        List<Object[]>respuesta = productoRepo.listaProductosYComentarios();
        //respuesta.forEach(System.out::println);
//        for(Object[] object: respuesta){
//            System.out.println(object[0]+"---"+object[1]);
//        }
        respuesta.forEach(objeto -> System.out.println(objeto[0]+"---"+objeto[1]+"---"+objeto[2]));
        Assertions.assertEquals(4, respuesta.size());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarProductosValidosTest(){
        List<ProductoValido>productos = productoRepo.listarProductosValidos(LocalDateTime.now());
        productos.forEach(System.out::println);
    }

}
