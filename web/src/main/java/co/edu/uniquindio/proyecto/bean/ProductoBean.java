package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.CategoriaEnum;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @Getter @Setter
    private List<CategoriaEnum>categorias;

    @PostConstruct
    public void inicializar(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias = productoServicio.listarCategorias();
    }

    public void crearProducto(){
        try {
            if(!imagenes.isEmpty()){
                Usuario usuario = usuarioServicio.obtenerUsuario("123");
                producto.setUsuario(usuario);
                producto.setImagenes(imagenes);
                producto.setFechaLimite(LocalDate.now().plusMonths(2));
                productoServicio.publicarProducto(producto);

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Producto creado");
                FacesContext.getCurrentInstance().addMessage(null,facesMessage);
            }else{
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,"Alerta","Es necesario subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null,facesMessage);
            }
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,facesMessage);
        }
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen != null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){
        try {
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
//falta organizar el menu y poner a funcionar la subida de las imagenes
//video 15