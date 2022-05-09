/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Modulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author DarioA
 */
public class ModuloManager extends AbstractJpaDao<Modulo> {

    public List<Modulo> getAll() {
        return this.getAll(Modulo.class);
    }

    public Modulo getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT m FROM Modulo m where idModulo= :id");
            query.setParameter("id", id);
            return (Modulo) query.getSingleResult();
        } catch (Exception e) {
            return new Modulo();
        } finally {
        }

    }
}
