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
 * @author DarioA
 */
public class BacklogManager  extends AbstractJpaDao<Backloog> {
    
     public Backloog getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Backloog m where idBackloog= :id");
            query.setParameter("id", id);
            return (Backloog) query.getSingleResult();
        } catch (Exception e) {
            return new Backloog();
        } finally {
        }

    }
     
     public Backloog getByProyecto(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Backloog m where a_proyecto_id_proyecto= :id");
            query.setParameter("id", id);
            return (Backloog) query.getSingleResult();
        } catch (Exception e) {
            return new Backloog();
        } finally {
        }

    }
     
     
    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(p.idBackloog)+1 FROM Backloog p");
            return  query.getSingleResult() != null ?(Integer) query.getSingleResult() : 1;
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }
}
