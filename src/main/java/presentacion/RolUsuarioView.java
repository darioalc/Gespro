/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Rol;
import entity.RolUsuario;
import entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.RolManager;
import manager.RolUsuarioManager;
import manager.UsuarioManager;
import org.primefaces.PrimeFaces;

@Model
@SessionScoped
public class RolUsuarioView implements Serializable {

    RolUsuarioManager manager;
    RolUsuario rolUsuario;
    Integer idUsuario;
    Integer rol;
    UsuarioManager usuarioManager;
    RolManager rolManager;
    List<Usuario> usuarios;
    List<Rol> roles;
    List<RolUsuario> rolUsuarioList;

    @PostConstruct
    public void init() {
        manager = new RolUsuarioManager();
        usuarioManager = new UsuarioManager();
        rolManager = new RolManager();
        rolUsuarioList = manager.getAll();
        usuarios = usuarioManager.getAll();
        roles = rolManager.getAll();
    }

    public void save() {
        Usuario usr = usuarioManager.getById(idUsuario);
        Rol r = rolManager.getById(rol);
        rolUsuario.setIdRol(r);
        rolUsuario.setIdUsuario(usr);
        if (rolUsuario.getId() != null) {
            manager.update(rolUsuario);
        } else {
            rolUsuario.setId(manager.nextId());
            manager.create(rolUsuario);
        }
        rolUsuarioList = manager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public void clear() {
        idUsuario = null;
        rol = null;
        rolUsuario = new RolUsuario();
        PrimeFaces.current().ajax().update("from2");
    }

    public void edit() {
        idUsuario = rolUsuario.getIdUsuario().getIdUsuario();
        rol = rolUsuario.getIdRol().getIdRol();
        PrimeFaces.current().ajax().update("from2");
    }

    public void delete() {
        manager.delete(rolUsuario);
        rolUsuarioList = manager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<RolUsuario> getRolUsuarioList() {
        return rolUsuarioList;
    }

    public void setRolUsuarioList(List<RolUsuario> rolUsuarioList) {
        this.rolUsuarioList = rolUsuarioList;
    }

}
