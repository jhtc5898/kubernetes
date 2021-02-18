package gestion;

import java.util.List;

import javax.ejb.Local;

import modelo.Usuario;

/**
*
* @author John
*/
@Local
public interface GestionUsuarioLocal {

   public void insertUsuario(Usuario u);

   public List<Usuario> getUsuarios();

   
}

