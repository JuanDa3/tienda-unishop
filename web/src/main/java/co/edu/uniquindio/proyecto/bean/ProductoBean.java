package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    private final ProductoServicio productoServicio;

    private final UsuarioServicio usuarioServicio;

    private final CategoriaServicio categoriaServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @Getter @Setter
    private List<Categoria>categorias;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    public ProductoBean(ProductoServicio productoServicio, UsuarioServicio usuarioServicio, CategoriaServicio categoriaServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
        this.categoriaServicio = categoriaServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias = categoriaServicio.listaCategorias();

    }

    public void crearProducto(){
        try {
            if(usuarioSesion != null){
                if(!imagenes.isEmpty()){
                    producto.setUsuario(usuarioSesion);
                    producto.setImagenes(imagenes);
                    producto.setFechaLimite(LocalDate.now().plusMonths(2));
                    productoServicio.publicarProducto(producto);

                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Producto creado");
                    FacesContext.getCurrentInstance().addMessage(null,facesMessage);
                }
            }
            else{
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