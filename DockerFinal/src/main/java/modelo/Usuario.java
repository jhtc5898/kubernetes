package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
*
* @author John
*/
@Entity
public class Usuario implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3016783111524290826L;
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String usuario;
   private String contrasenia;
   private String correo;

   @Override
   public String toString() {
       return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", correo=" + correo + '}';
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getUsuario() {
       return usuario;
   }

   public void setUsuario(String usuario) {
       this.usuario = usuario;
   }

   public String getContrasenia() {
       return contrasenia;
   }

   public void setContrasenia(String contrasenia) {
       this.contrasenia = contrasenia;
   }

   public String getCorreo() {
       return correo;
   }

   public void setCorreo(String correo) {
       this.correo = correo;
   }

}