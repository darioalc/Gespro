/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DarioA
 */
public abstract class AbstractJpaDao<T> {

    protected EntityManagerFactory emf;
    @PersistenceContext
    protected EntityManager em;

    public AbstractJpaDao() {
        emf = JpaResourceBean.getEMF();
    }

    public T findByName(String name, String namedQuery, String paramater) {
        if (em == null) {
            em = this.emf.createEntityManager();
        }
        try {
            return (T) em
                    .createNamedQuery(namedQuery)
                    .setParameter(paramater, name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void create(T entity) {
        if (em == null) {
            em = this.emf.createEntityManager();
        }
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public List<T> getAll(Class<T> tipo) {
        if (em == null) {
            em = this.emf.createEntityManager();
        }
        return em.createQuery("Select o from " + tipo.getName() + " o ").getResultList();
    }

    public T update(T entity) {
        if (em == null) {
            em = this.emf.createEntityManager();
        }
        em.getTransaction().begin();
        T t = em.merge(entity);
        em.getTransaction().commit();
        return t;
    }

    public void delete(T entity) {
        if (em == null) {
            em = this.emf.createEntityManager();
        }
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
