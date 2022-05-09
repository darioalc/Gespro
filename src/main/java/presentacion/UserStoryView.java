/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import entity.UsrHisto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import manager.UserStoryManager;

/**
 *
 * @author DarioA
 */
@Model
@SessionScoped
public class UserStoryView  implements Serializable{
    List<UsrHisto> list;
    UserStoryManager manager;
    
    @PostConstruct
    public void init() {
        manager= new UserStoryManager();
        list=manager.getAll(UsrHisto.class);
    }
    
    
    
}
