/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Permiso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PermisoManager extends AbstractJpaDao<Permiso> {

    public List<Permiso> getAll() {
        return this.getAll(Permiso.class);
    }

    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(p.idPermiso)+1 FROM Permiso p");
            return (Integer) query.getSingleResult();
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public Permiso getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT p FROM Permiso p where idPermiso=:id");
            query.setParameter("id", id);
            return (Permiso) query.getSingleResult();
        } catch (Exception e) {
            return new Permiso();
        } finally {
        }

    }
}
