package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario>buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El código ya existe");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        return usuarioRepo.save(u);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario login(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception(""));
    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        return usuarioRepo.findByCodigo(codigo).orElseThrow(() -> new Exception("El usuario no Existe"));
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));
    }
}
