package gestion;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.UsuarioDAO;
import modelo.Usuario;

/**
*
* @author John
*/
@Stateless
public class GestionUsuario implements GestionUsuarioLocal {

	@Inject
	private UsuarioDAO dao;
	
	@Override
	public void insertUsuario(Usuario usuario) {
		dao.insert(usuario);
	}

	@Override
	public List<Usuario> getUsuarios() {
		return dao.getUsuarios();
	}
       
 

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	
}
