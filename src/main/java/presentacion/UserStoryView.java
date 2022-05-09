/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.UsrHisto;
import entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.UsrHistoManager;
import manager.UsuarioManager;
import org.primefaces.PrimeFaces;

/**
 *
 * @author AdrianBorja
 */
@Model
@SessionScoped
public class UserStoryView  implements Serializable{
    List<UsrHisto> list;
    List<Usuario> listUsuario;
    UsrHistoManager manager;
     UsuarioManager usuarioManager;
     Usuario usuario;
     Integer idUsuario;
     UsrHisto usrHisto;
    
    @PostConstruct
    public void init() {
        usrHisto= new UsrHisto();
        usuario= new Usuario();
        manager= new UsrHistoManager();
        usuarioManager= new UsuarioManager();
        list=manager.getAll(UsrHisto.class);
        listUsuario=usuarioManager.getAll();
    }
    
       
    public void clear() {
        usrHisto = new UsrHisto();
        PrimeFaces.current().ajax().update("from2");
    }

    public void edit() {
        idUsuario = usuario.getIdUsuario();
        PrimeFaces.current().ajax().update("from2");
    }

    public void save() {
        usrHisto.setIdUsuario(idUsuario);
        if (usrHisto.getIdHistory()!= null) {
            manager.update(usrHisto);
        } else {
            usrHisto.setIdHistory(manager.nextId());
            manager.create(usrHisto);
        }

        list = manager.getAll(UsrHisto.class);
        PrimeFaces.current().ajax().update("from1");
    }

    public void delete() {
        manager.delete(usrHisto);
        list = manager.getAll(UsrHisto.class);
        PrimeFaces.current().ajax().update("from1");
    }

    public List<UsrHisto> getList() {
        return list;
    }

    public void setList(List<UsrHisto> list) {
        this.list = list;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsrHisto getUsrHisto() {
        return usrHisto;
    }

    public void setUsrHisto(UsrHisto usrHisto) {
        this.usrHisto = usrHisto;
    }
    
    
    
}
