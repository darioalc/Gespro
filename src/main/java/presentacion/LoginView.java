package presentacion;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AdrianBorja
 */
@Model
@SessionScoped
public class LoginView implements Serializable {

    private String usuario;
    private String contraseña;

//    @Inject
//    LoginService loginService;
//
    public String usuarioValido() {
//        if (loginService.validate(new LoginModelRequest(usuario, contraseña))) {
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
