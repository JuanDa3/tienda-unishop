package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {
    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    public void registrarAdministradorTest(){
        Administrador administrador = Administrador.builder()
                .codigo("123").nombre("juan").email("juan@mail.com")
                .password("123").build();
        Administrador administradorGuardado = administradorRepo.save(administrador);
        Assertions.assertNotNull(administradorGuardado);
    }

    @Test
    public void eliminarAdministradorTest(){
        Administrador administrador = Administrador.builder()
                .codigo("123").nombre("juan").email("juan@mail.com")
                .password("123").build();
        Administrador administradorGuardado = administradorRepo.save(administrador);
        administradorRepo.deleteById("123");

        Administrador administradorBuscado = administradorRepo.findById("123").orElse(null);
        Assertions.assertNull(administradorBuscado);
    }

    @Test
    public void actualizarAdministradorTest(){
        Administrador administrador = Administrador.builder()
                .codigo("123").nombre("juan").email("juan@mail.com")
                .password("123").build();
        Administrador administradorGuardado = administradorRepo.save(administrador);
        administradorGuardado.setNombre("juan camilo");
        administradorRepo.save(administradorGuardado);

        Administrador administradorBuscado = administradorRepo.findById("123").orElse(null);
        Assertions.assertEquals("juan camilo", administradorBuscado.getNombre());
    }

    @Test
    @Sql("classpath:dataSet.sql")
    public void listarAdministradoresTest(){
        
        List<Administrador>administradores = administradorRepo.findAll();
        administradores.forEach(a -> System.out.println(a));
    }
}
