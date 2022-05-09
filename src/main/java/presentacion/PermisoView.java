/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Modulo;
import entity.Permiso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.ModuloManager;
import manager.PermisoManager;
import org.primefaces.PrimeFaces;

@Model
@SessionScoped
public class PermisoView implements Serializable {

    PermisoManager permisoManager;
    List<Permiso> permisos;
    Permiso permiso;
    List<Modulo> modulos;
    Integer moduloSelected;
    ModuloManager moduloManager;

    @PostConstruct
    public void init() {
        moduloManager = new ModuloManager();
        permiso = new Permiso();
        modulos = moduloManager.getAll();
        permiso.setAModuloIdModulo(new Modulo());
        permisoManager = new PermisoManager();
        permisos = permisoManager.getAll();
    }

    public List<Permiso> getPermisoes() {
        return permisos;
    }

    public void setPermisoes(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public void clear() {
        permiso = new Permiso();
        PrimeFaces.current().ajax().update("from2");
    }

    public void edit() {
        moduloSelected = permiso.getAModuloIdModulo().getIdModulo();
        PrimeFaces.current().ajax().update("from2");
    }

    public void save() {
        permiso.setAModuloIdModulo(moduloManager.getById(moduloSelected));
        permiso.setIdModulo(moduloSelected);
        if (permiso.getIdPermiso() != null) {
            permisoManager.update(permiso);
        } else {
            permiso.setIdPermiso(permisoManager.nextId());
            permisoManager.create(permiso);
        }

        permisos = permisoManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public void delete() {
        permisoManager.delete(permiso);
        permisos = permisoManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Integer getModuloSelected() {
        return moduloSelected;
    }

    public void setModuloSelected(Integer moduloSelected) {
        this.moduloSelected = moduloSelected;
    }

}
