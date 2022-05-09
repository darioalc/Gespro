/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Backloog;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author vgomez
 */
public class BackloogManager   extends AbstractJpaDao<Backloog>{
    
     public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(u.idBackloog)+1 FROM Backloog u");
            return query.getSingleResult()!=null ? (Integer) query.getSingleResult(): 1;
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public Backloog getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM Backloog u where idBackloog=:id");
            query.setParameter("id", id);
            return (Backloog) query.getSingleResult();
        } catch (Exception e) {
            return new Backloog();
        } finally {
        }

    }
}
