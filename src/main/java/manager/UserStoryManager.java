/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.UsrHisto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author DarioA
 */
public class UserStoryManager  extends AbstractJpaDao<UsrHisto>{
    
    
    public List<UsrHisto> getStoriesByBacklog(Integer id){
         try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT m FROM UsrHisto m where a_backloog_id_backloog= :id");
            query.setParameter("id", id);
            return (List<UsrHisto>) query.getResultList();
        } catch (Exception e) {
            return new ArrayList();
        } finally {
        }
    }
}
