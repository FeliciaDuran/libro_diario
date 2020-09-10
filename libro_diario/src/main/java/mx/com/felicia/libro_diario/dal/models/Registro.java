package mx.com.felicia.libro_diario.dal.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registros database table.
 * 
 */
@Entity
@Table(name="registros")
@NamedQuery(name="Registro.findAll", query="SELECT r FROM Registro r")
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_registro")
	private int idRegistro;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float monto;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Operacion
	@ManyToOne
	@JoinColumn(name="id_operacion")
	private Operacion operacione;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuarios usuario;

	public Registro() {
	}

	public int getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Operacion getOperacione() {
		return this.operacione;
	}

	public void setOperacione(Operacion operacione) {
		this.operacione = operacione;
	}

	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

}