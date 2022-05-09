/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Estado;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author DarioA
 */
public class EstadoManagr extends AbstractJpaDao<Estado>  {
    
    
     public Estado getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Estado m where idEstado= :id");
            query.setParameter("id", id);
            return (Estado) query.getSingleResult();
        } catch (Exception e) {
            return new Estado();
        } finally {
        }

    }
}
