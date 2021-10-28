package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Test
    public void registrarCategoriaTest(){
        Categoria categoria = new Categoria("123","muebles");
        Categoria categoriaGuardado = categoriaRepo.save(categoria);
        Assertions.assertNotNull(categoriaGuardado);
    }

    @Test
    public void eliminarCategoriaTest(){
        Categoria categoria = new Categoria("123","muebles");
        Categoria categoriaGuardado = categoriaRepo.save(categoria);
        categoriaRepo.deleteById("123");

        Categoria categoriaBuscado = categoriaRepo.findById("123").orElse(null);
        Assertions.assertNull(categoriaBuscado);
    }

    @Test
    public void actualizarCategoriaTest(){
        Categoria categoria = new Categoria("123","muebles");

        Categoria categoriaGuardado = categoriaRepo.save(categoria);
        categoriaGuardado.setNombre("electrodomesticos");
        categoriaRepo.save(categoriaGuardado);

        Categoria categoriaBuscado = categoriaRepo.findById("123").orElse(null);
        Assertions.assertEquals("electrodomesticos", categoriaBuscado.getNombre());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarCategoriasTest(){
        List<Categoria>categorias = categoriaRepo.findAll();
        categorias.forEach(c -> System.out.println(c));
    }
}
