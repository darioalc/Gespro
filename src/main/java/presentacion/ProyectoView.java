/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Estado;
import entity.Modulo;
import entity.Proyecto;
import entity.RolUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.EstadoManagr;
import manager.ModuloManager;
import manager.ProyectoManager;
import manager.RolUsuarioManager;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author DarioA
 */
@Model
@SessionScoped
public class ProyectoView  implements Serializable {
     ProyectoManager proyectoManager;
    List<Proyecto> proyectos;
    Proyecto proyecto;
    List<RolUsuario>  rolUsuarios;
    List<RolUsuario>  rolUsuariosSeleccionados;
    List<Estado> estados;
    Estado estado;
    Integer estadoId;
    Integer moduloSelected;
    RolUsuarioManager rolUsuarioManager;
    EstadoManagr estadoManagr;
    private DualListModel<RolUsuario> roles;
    RolUsuario rol;
    
    @PostConstruct
    public void init() {
        estadoId=1;
        rol= new RolUsuario();
        estado= new Estado();
        estadoManagr= new EstadoManagr();
        estados= estadoManagr.getAll(Estado.class);
        rolUsuarioManager = new RolUsuarioManager();
        proyecto = new Proyecto();
        proyecto.setEstadosIdEstados(new Estado());
        proyectoManager = new ProyectoManager();
        proyectos = proyectoManager.getAll();
        rolUsuariosSeleccionados =new ArrayList<>();
        rolUsuarios= rolUsuarioManager.getAll();
        roles =new DualListModel<>(rolUsuarios, rolUsuariosSeleccionados);
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public void clear() {
        rolUsuarios= rolUsuarioManager.getAll();
        rolUsuariosSeleccionados =new ArrayList<>();
        proyecto = new Proyecto();
        PrimeFaces.current().ajax().update("from2");
    }

    public String edit() {
        estado = proyecto.getEstadosIdEstados();
        rolUsuariosSeleccionados = proyecto.getRolUsuarioList();
        PrimeFaces.current().ajax().update("from2");
        return "proyectoNew";
    }
    
     public String add() {
        estado = null;
        clear();
        PrimeFaces.current().ajax().update("from2");
        return "proyectoNew";
    }

    public String save() {
        estado = estadoManagr.getById(estadoId);
        proyecto.setEstadosIdEstados(estado);
        proyecto.setRolUsuarioList(rolUsuariosSeleccionados);
        if (proyecto.getIdProyecto() != null) {
            proyectoManager.update(proyecto);
        } else {
            proyecto.setIdProyecto(proyectoManager.nextId());
            proyectoManager.create(proyecto);
        }

        proyectos = proyectoManager.getAll();
        PrimeFaces.current().ajax().update("from1");
        return "proyecto";
    }

    public void delete() {
        proyectoManager.delete(proyecto);
        proyectos = proyectoManager.getAll();
        PrimeFaces.current().ajax().update("from1");
    }
    
    public void aggRol() {
        rolUsuariosSeleccionados.add(rol);
        rolUsuarios.remove(rol);
        PrimeFaces.current().ajax().update("from2");
    }
    
    public void deleteRol() {
        rolUsuariosSeleccionados.remove(rol);
        rolUsuarios.add(rol);
        PrimeFaces.current().ajax().update("from2");
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

   
    public Integer getModuloSelected() {
        return moduloSelected;
    }

    public void setModuloSelected(Integer moduloSelected) {
        this.moduloSelected = moduloSelected;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<RolUsuario> getRolUsuarios() {
        return rolUsuarios;
    }

    public void setRolUsuarios(List<RolUsuario> rolUsuarios) {
        this.rolUsuarios = rolUsuarios;
    }

    public List<RolUsuario> getRolUsuariosSeleccionados() {
        return rolUsuariosSeleccionados;
    }

    public void setRolUsuariosSeleccionados(List<RolUsuario> rolUsuariosSeleccionados) {
        this.rolUsuariosSeleccionados = rolUsuariosSeleccionados;
    }

    public DualListModel<RolUsuario> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<RolUsuario> roles) {
        this.roles = roles;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }
  
}
