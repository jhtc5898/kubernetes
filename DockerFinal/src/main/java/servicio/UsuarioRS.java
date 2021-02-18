package servicio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import gestion.GestionUsuarioLocal;
import modelo.Usuario;



@Stateless
@Path("/usuarios")
public class UsuarioRS {
	
	@Inject
	private GestionUsuarioLocal gul;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuarios(){
		List<Usuario> usuarios = gul.getUsuarios();
		System.out.println("Usuarios Encontrados: "+usuarios);
		return usuarios;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario agregarUsuario(Usuario usu) {
		gul.insertUsuario(usu);
		System.out.println("Agregado: "+usu);
		return usu;
	}
}
