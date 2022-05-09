package presentacion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import manager.UsuarioManager;

/**
 *
 * @author user
 */
@Model
@SessionScoped
public class LoginView implements Serializable {

    private String usuario;
    private String contraseña;

    UsuarioManager usuarioManager;

    @PostConstruct
    public void init() {
        usuarioManager = new UsuarioManager();
    }

    public String usuarioValido() {
//        if (usuarioManager.login(usuario, contraseña)) {
            return "home";
//        }
//        return "login";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        return "login";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
