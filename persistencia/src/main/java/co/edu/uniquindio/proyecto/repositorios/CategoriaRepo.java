package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria,String> {

    @Query("select c.nombre, avg (cf.calificacion) as calificacion from Categoria c join c.productos p join p.comentarios cf order by calificacion desc")
    List<Object>listaCatePorCalif();

    @Query("Select s from Producto p join p.subastas s join p.categorias c  where c.nombre = :nombreCategoria and s.fechaLimite >= :fecha")
    List<Object>subastaCat(String nombreCategoria, LocalDate fecha);
}
