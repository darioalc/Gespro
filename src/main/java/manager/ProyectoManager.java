/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author vgomez
 */
public class ProyectoManager extends AbstractJpaDao<Proyecto>{
      public List<Proyecto> getAll() {
        return this.getAll(Proyecto.class);
    }

    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(p.idProyecto)+1 FROM Proyecto p");
            return query.getSingleResult()!=null ? (Integer) query.getSingleResult() : 1;
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public Proyecto getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT p FROM Proyecto p where idProyecto=:id");
            query.setParameter("id", id);
            return (Proyecto) query.getSingleResult();
        } catch (Exception e) {
            return new Proyecto();
        } finally {
        }

    }
}
