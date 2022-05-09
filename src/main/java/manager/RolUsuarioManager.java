/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.RolUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RolUsuarioManager extends AbstractJpaDao<RolUsuario> {

    public List<RolUsuario> getAll() {
        return this.getAll(RolUsuario.class);
    }

    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(u.id)+1 FROM RolUsuario u");
            return (Integer) query.getSingleResult() == null ? 1 : (Integer) query.getSingleResult();
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public void updateProyecto(Integer idProyecto) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT p FROM RolUsuario set idProyecto=:idProyecto where idPermiso=:id");
            query.setParameter("id", id);
            return (Permiso) query.getSingleResult();
        } catch (Exception e) {
            return new Permiso();
        } finally {
        }

    }
}
