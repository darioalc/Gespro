/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.UsrHisto;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author vgomez
 */
public class UsrHistoManager  extends AbstractJpaDao<UsrHisto>{
    
    
    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(u.idUsrHisto)+1 FROM UsrHisto u");
            return query.getSingleResult()!=null ? (Integer) query.getSingleResult(): 1;
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public UsrHisto getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM UsrHisto u where idUsrHisto=:id");
            query.setParameter("id", id);
            return (UsrHisto) query.getSingleResult();
        } catch (Exception e) {
            return new UsrHisto();
        } finally {
        }

    }
}
