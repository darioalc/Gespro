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
import manager.BacklogManager;
import manager.ProyectoManager;

/**
 *
 * @author DarioA
 */
@Model
@SessionScoped
public class BacklogView implements Serializable {

    List<Proyecto> listProyecto;
    ProyectoManager manager;
    Proyecto proyecto;
    Backloog backloog;
    BacklogManager backlogManager;

    @PostConstruct
    public void init() {
        backloog = new Backloog();
        proyecto = new Proyecto();
        manager = new ProyectoManager();
        backlogManager = new BacklogManager();
        listProyecto = manager.getAll();
    }

    public String save() {
        backloog.setAProyectoIdProyecto(proyecto);
        if (backloog.getIdBackloog() != null) {
            backlogManager.update(backloog);
        } else {
            backloog.setIdBackloog(backlogManager.nextId());
            backlogManager.create(backloog);
        }
        return "backlog";
    }

    public String aggBacklog() {
        try {
            backloog = backlogManager.getByProyecto(proyecto.getIdProyecto());
        } catch (Exception e) {
            backloog = new Backloog();
        }
        return "backlogNew";
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

    public Backloog getBackloog() {
        return backloog;
    }

    public void setBackloog(Backloog backloog) {
        this.backloog = backloog;
    }

}
