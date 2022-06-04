/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.Backloog;
import entity.Proyecto;
import entity.RolUsuario;
import entity.UsrHisto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manager.BacklogManager;
import manager.ProyectoManager;
import manager.RolUsuarioManager;
import manager.UserStoryManager;
import org.primefaces.PrimeFaces;

/**
 *
 * @author DarioA
 */
@Model
@SessionScoped
public class UserStoryView implements Serializable {

    List<Proyecto> listProyecto;
    ProyectoManager manager;
    List<Backloog> listBackloog;
    Proyecto proyecto;
    Backloog backloog;
    BacklogManager backlogManager;
    UsrHisto histo;
    List<UsrHisto> histoList;
    List<RolUsuario> rolUsuarios;
    RolUsuarioManager rolUsuarioManager;
    UserStoryManager userStoryManager;

    @PostConstruct
    public void init() {
        userStoryManager = new UserStoryManager();
        histoList = new ArrayList();
        backloog = new Backloog();
        proyecto = new Proyecto();
        histo = new UsrHisto();
        manager = new ProyectoManager();
        backlogManager = new BacklogManager();
        listProyecto = manager.getAll();
        listBackloog = backlogManager.getAll(Backloog.class);
        rolUsuarioManager = new RolUsuarioManager();
    }

    public String aggStory() {
        rolUsuarios = rolUsuarioManager.getRolUsuarioByPoyecto(backloog.getAProyectoIdProyecto().getIdProyecto());
        return "userStoryNew";
    }

    public void addstory() {
        if (histo.getNombreHistory()== null || histo.getEsfuerzo() == null || histo.getPrioridad() == null || histo.getIdUsuario() == 0) {
            FacesContext.getCurrentInstance().addMessage("Error ", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Complete todos los campos (*)"));
        } else {
            histoList.add(histo);
            PrimeFaces.current().ajax().update("from2");
            histo = new UsrHisto();
        }
    }

    public String save() {
        if(histoList.isEmpty()){
             FacesContext.getCurrentInstance().addMessage("Error ", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Debe existir al menos una historia"));
            return null;
        }else{
           for (UsrHisto historia : histoList) {
            historia.setABackloogIdBackloog(backloog);
            userStoryManager.create(historia);
        }
        return "userStory";  
        }
       
    }

    public List<Proyecto> getListProyecto() {
        return listProyecto;
    }

    public void setListProyecto(List<Proyecto> listProyecto) {
        this.listProyecto = listProyecto;
    }

    public List<Backloog> getListBackloog() {
        return listBackloog;
    }

    public void setListBackloog(List<Backloog> listBackloog) {
        this.listBackloog = listBackloog;
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

    public UsrHisto getHisto() {
        return histo;
    }

    public void setHisto(UsrHisto histo) {
        this.histo = histo;
    }

    public List<UsrHisto> getHistoList() {
        return histoList;
    }

    public void setHistoList(List<UsrHisto> histoList) {
        this.histoList = histoList;
    }

    public List<RolUsuario> getRolUsuarios() {
        return rolUsuarios;
    }

    public void setRolUsuarios(List<RolUsuario> rolUsuarios) {
        this.rolUsuarios = rolUsuarios;
    }

}
