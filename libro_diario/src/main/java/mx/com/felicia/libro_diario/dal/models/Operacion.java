package mx.com.felicia.libro_diario.dal.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the operaciones database table.
 * 
 */
@Entity
@Table(name="operaciones")
@NamedQuery(name="Operacion.findAll", query="SELECT o FROM Operacion o")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_operacion")
	private int idOperacion;

	@Column(name="nombre_operacion")
	private String nombreOperacion;

	//bi-directional many-to-one association to Registro
	@OneToMany(mappedBy="operacione")
	private List<Registro> registros;

	public Operacion() {
	}

	public int getIdOperacion() {
		return this.idOperacion;
	}

	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getNombreOperacion() {
		return this.nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public List<Registro> getRegistros() {
		return this.registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public Registro addRegistro(Registro registro) {
		getRegistros().add(registro);
		registro.setOperacione(this);

		return registro;
	}

	public Registro removeRegistro(Registro registro) {
		getRegistros().remove(registro);
		registro.setOperacione(null);

		return registro;
	}

}