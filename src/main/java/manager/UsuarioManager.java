/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class UsuarioManager extends AbstractJpaDao<Usuario> {

    public boolean login(String usuario, String pass) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.pass=:pass");
            query.setParameter("usuario", usuario);
            query.setParameter("pass", pass);
            Usuario user = (Usuario) query.getSingleResult();
            return user!=null;
        } catch (Exception e) {
            return false;
        }finally{
            em.close();
        }

    }

}
