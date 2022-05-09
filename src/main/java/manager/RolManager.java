/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Rol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RolManager extends AbstractJpaDao<Rol> {

    public List<Rol> getAll() {
        return this.getAll(Rol.class);
    }

    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(R.idRol)+1 FROM Rol R");
            return (Integer) query.getSingleResult() == null? 1: (Integer) query.getSingleResult();
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }
    
    
      public Rol getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT r FROM Rol r where idRol=:id");
            query.setParameter("id", id);
            return (Rol) query.getSingleResult();
        } catch (Exception e) {
            return new Rol();
        } finally {
        }

    }
}
