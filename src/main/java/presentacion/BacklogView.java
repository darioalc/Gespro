/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Backloog;
import entity.Proyecto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.BackloogManager;
import manager.ProyectoManager;
import org.primefaces.PrimeFaces;

/**
 *
 * @author AdrianBorja
 */
@Model
@SessionScoped
public class BacklogView implements Serializable {

    List<Backloog> list;
    List<Proyecto> listProyecto;
    BackloogManager manager;
    Backloog backlog;
    Proyecto proyecto; 
    ProyectoManager managerProyecto;
    Integer idProyecto;

    public BacklogView() {
    }

    @PostConstruct
    public void init() {
        managerProyecto= new ProyectoManager();
        proyecto= new Proyecto();
        backlog = new Backloog();
        manager = new BackloogManager();
        list = manager.getAll(Backloog.class);
        listProyecto= managerProyecto.getAll();
    }

    
    public void clear() {
        backlog = new Backloog();
        PrimeFaces.current().ajax().update("from2");
    }

    public void edit() {
        idProyecto = proyecto.getIdProyecto();
        PrimeFaces.current().ajax().update("from2");
    }

    public void save() {
        backlog.setIdProyecto(idProyecto);
        if (backlog.getIdBackloog()!= null) {
            manager.update(backlog);
        } else {
            backlog.setIdBackloog(manager.nextId());
            manager.create(backlog);
        }

        list = manager.getAll(Backloog.class);
        PrimeFaces.current().ajax().update("from1");
    }

    public void delete() {
        manager.delete(backlog);
        list = manager.getAll(Backloog.class);
        PrimeFaces.current().ajax().update("from1");
    }
    
    public List<Backloog> getList() {
        return list;
    }

    public void setList(List<Backloog> list) {
        this.list = list;
    }

    public Backloog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backloog backlog) {
        this.backlog = backlog;
    }

    public List<Proyecto> getListProyecto() {
        return listProyecto;
    }

    public void setListProyecto(List<Proyecto> listProyecto) {
        this.listProyecto = listProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

}
