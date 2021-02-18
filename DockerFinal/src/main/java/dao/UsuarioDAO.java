package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;

import modelo.Usuario;

/**
*
* @author John
*/
@Stateless
public class UsuarioDAO {

   @Inject
   private EntityManager em;

   public void insert(Usuario usuario) {
       this.em.persist(usuario);
       this.em.flush();
   }

   public List<Usuario> getUsuarios() {
       String jpql = "SELECT u FROM Usuario u";
       Query query = this.em.createQuery(jpql, Usuario.class);
       List<Usuario> usuarioList = query.getResultList();
       return usuarioList;
   }

}
