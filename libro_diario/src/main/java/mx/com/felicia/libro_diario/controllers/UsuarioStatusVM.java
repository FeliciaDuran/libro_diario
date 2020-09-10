package mx.com.felicia.libro_diario.controllers;

import mx.com.felicia.libro_diario.dal.models.Usuarios;

public class UsuarioStatusVM {
	private Usuarios usuario;
	private boolean status;
	
	public UsuarioStatusVM(Usuarios usuario, boolean status) {
		super();
		this.usuario = usuario;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Usuarios getUsuario() {
		return usuario;
	}
	
}
