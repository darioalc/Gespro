/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Permiso;
import entity.Rol;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.PermisoManager;
import manager.RolManager;
import org.primefaces.PrimeFaces;

/**
 *
 * @author user
 */
@Model
@SessionScoped
public class RolView implements Serializable {

    RolManager rolManager;
    PermisoManager permisoManager;
    List<Rol> roles;
    List<Permiso> permisos;
    Integer permisoSelected;
    Rol rol;
    boolean editarB;
    boolean verB;

    @PostConstruct
    public void init() {
        rol = new Rol();
        permisoManager = new PermisoManager();
        rolManager = new RolManager();
        permisos = permisoManager.getAll();
        roles = rolManager.getAll();
    }

    public void clear() {
        rol = new Rol();
        PrimeFaces.current().ajax().update("from2");
    }

    public void edit() {
        permisoSelected = rol.getAPermisoIdPermiso().getIdPermiso();
        PrimeFaces.current().ajax().update("from2");
    }

    public void delete() {
        rolManager.delete(rol);
        roles = rolManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public void save() {
        Permiso p = permisoManager.getById(permisoSelected);
        rol.setAPermisoIdPermiso(p);
        rol.setIdPermiso(permisoSelected);
        if (rol.getIdRol() != null) {
            rolManager.update(rol);
        } else {
            rol.setIdRol(rolManager.nextId());
            rolManager.create(rol);
        }
        roles = rolManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void editar() {
        verB = false;
        editarB = true;
        PrimeFaces.current().ajax().update("from2");
    }

    public void ver() {
        verB = true;
        editarB = false;
        PrimeFaces.current().ajax().update("from2");
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

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Integer getPermisoSelected() {
        return permisoSelected;
    }

    public void setPermisoSelected(Integer permisoSelected) {
        this.permisoSelected = permisoSelected;
    }

}
