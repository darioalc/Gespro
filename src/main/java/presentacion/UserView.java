package presentacion;

import entity.Rol;
import entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.UsuarioManager;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;

/**
 *
 * @author joha
 */
@Model
@SessionScoped
public class UserView implements Serializable {

    UsuarioManager usuarioManager;
    Usuario usuario;
    boolean editarB;
    boolean verB;
    boolean newB;

    List<Usuario> users;
    List<Rol> rols;

    @PostConstruct
    public void init() {
        rols = new ArrayList();
        usuario = new Usuario();
        usuarioManager = new UsuarioManager();
        users = usuarioManager.getAll();
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public void clear() {
        newB = true;
        verB = false;
        editarB = false;
        usuario = new Usuario();
         PrimeFaces.current().ajax().update("from2");
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void save() {
        if (usuario != null) {
            if (usuario.getIdUsuario() == null) {
                usuario.setIdUsuario(usuarioManager.nextId());
                usuarioManager.saveUser(usuario);
            } else {
                usuarioManager.update(usuario);
            }
        }
        users = usuarioManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public void editar() {
        verB = false;
        editarB = true;
        newB = false;

        PrimeFaces.current().ajax().update("from2");
    }

    public void ver() {
        verB = true;
        newB = false;
        editarB = false;
        PrimeFaces.current().ajax().update("from2");
    }

    public void update() {
        usuarioManager.update(usuario);
    }

    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }

    public boolean isEditarB() {
        return editarB;
    }

    public void setEditarB(boolean editarB) {
        this.editarB = editarB;
    }

    public boolean isVerB() {
        return verB;
    }

    public void setVerB(boolean verB) {
        this.verB = verB;
    }

    public boolean isNewB() {
        return newB;
    }

    public void setNewB(boolean newB) {
        this.newB = newB;
    }

}
