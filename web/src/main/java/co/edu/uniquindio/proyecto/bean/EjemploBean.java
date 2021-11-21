package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@Getter
@Setter
public class EjemploBean implements Serializable {
    private String att1, att2;

    public void cambiar(){
        String aux = att1;
        att1 = att2;
        att2 = aux;
    }
}
