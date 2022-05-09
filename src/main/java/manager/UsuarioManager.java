/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.AbstractJpaDao;
import entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class UsuarioManager extends AbstractJpaDao<Usuario> {

    public List<Usuario> getAll() {
        return this.getAll(Usuario.class);
    }

    public boolean login(String usuario, String pass) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.pass=:pass");
            query.setParameter("usuario", usuario);
            query.setParameter("pass", pass);
            Usuario user = (Usuario) query.getSingleResult();
            return user != null;
        } catch (Exception e) {
            return false;
        } finally {
        }

    }

    public Integer nextId() {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT max(u.idUsuario)+1 FROM Usuario u");
            return (Integer) query.getSingleResult();
        } catch (Exception e) {
            return 1;
        } finally {
        }

    }

    public Usuario getById(Integer id) {
        try {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT u FROM Usuario u where idUsuario=:id");
            query.setParameter("id", id);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return new Usuario();
        } finally {
        }

    }

    public void saveUser(Usuario u) {
        this.create(u);
    }

}
