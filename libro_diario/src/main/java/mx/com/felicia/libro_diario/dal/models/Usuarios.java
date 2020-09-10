package mx.com.felicia.libro_diario.dal.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@NamedQuery(name="Usuarios.findAll", query="SELECT u FROM Usuarios u")
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	@Column(name="contrasena")
	private String contrasena;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="usuario")
	private String usuario;

	public Usuarios() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}