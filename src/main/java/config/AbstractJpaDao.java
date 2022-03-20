/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DarioA
 */
public abstract class AbstractJpaDao<T>  {
    protected EntityManagerFactory emf;
    @PersistenceContext
    protected EntityManager em;

    public AbstractJpaDao() {
        emf=JpaResourceBean.getEMF();
    }
    
    public T findByName(String name,String namedQuery,String paramater) {
        try {
            return (T) em
                .createNamedQuery(namedQuery)
                .setParameter(paramater, name)
                .getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
    
    
     public void create( T entity ){
      em.persist( entity );
   }

   public T update( T entity ){
      return em.merge( entity );
   }

   public void delete( T entity ){
      em.remove( entity );
   }
}
