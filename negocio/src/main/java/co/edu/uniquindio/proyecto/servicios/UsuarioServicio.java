package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    List<Usuario> listarUsuarios();

    Usuario login(String email, String password) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    Usuario iniciarSesion(String email, String password) throws Exception;
}
