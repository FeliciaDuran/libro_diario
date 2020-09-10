package mx.com.felicia.libro_diario.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

import mx.com.felicia.libro_diario.dal.models.Usuarios;
import mx.com.felicia.libro_diario.dal.repositories.UsuariosRepository;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UsuarioVM {

	@WireVariable
	private UsuariosRepository usuariosRepository;
	@Wire
	private List<Usuarios> listUsuario = new ArrayList<>();
	@Wire
	private boolean addUsuario = false;
	@Wire
	private boolean toUpdate = false;
	@Wire
	private boolean rowUpdate = false;
	@Wire
	private Usuarios currentUsuarios;

	@Init
	public void init() {
		listUsuario = usuariosRepository.findAll();
		currentUsuarios = new Usuarios();
	}

	@NotifyChange({ "currentUsuarios", "addUsuario" })
	@Command
	public void showOrHiddeAddUser(@BindingParam("selection") int selection) {

		if (selection == 1) {
			addUsuario = true;
		} else {
			addUsuario = false;
			currentUsuarios = new Usuarios();
		}

	}

	@NotifyChange({ "rowUpdate", "toUpdate" })
	@Command
	public void showEditable() {
		toUpdate = true;
		rowUpdate = true;
	}

	@NotifyChange({ "currentUsuarios", "addUsuario", "listUsuario" })
	@Command
	public void insertAndSave() {
		if (validateInsert()) {
			listUsuario.add(currentUsuarios);
			usuariosRepository.save(listUsuario);
			currentUsuarios = new Usuarios();
			addUsuario = false;
		} else {
			Messagebox.show("No se permiten campos vacios");
		}
	}

	@NotifyChange("listUsuario")
	@Command
	public void delete(@BindingParam("idToDelete") int idToDelete,
			@BindingParam("nombreCompleto") String nombreCompleto) {

		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
			public void onEvent(ClickEvent event) throws Exception {
				if (Messagebox.Button.YES.equals(event.getButton())) {
					usuariosRepository.delete(idToDelete);
					BindUtils.postNotifyChange(null, null, this, "listUsuario");
				}
			}
		};

		Messagebox.show("¿Do you want to delete " + nombreCompleto + "?", "Message",
				new Messagebox.Button[] { Messagebox.Button.YES, Messagebox.Button.NO }, Messagebox.QUESTION,
				clickListener);

	}

	@NotifyChange({ "rowUpdate", "toUpdate", "currentUsuarios" })
	@Command
	public void confirm(@BindingParam("currentUsuarios") Usuarios currentUsuarios) {
		usuariosRepository.save(currentUsuarios);
		refreshRowTemplate(currentUsuarios);
		currentUsuarios = new Usuarios();
		rowUpdate = false;
		toUpdate = false;
	}
//
//	@NotifyChange({ "toUpdate", "rowUpdate", "currentUsuarios" })
//	@Command
//	public void update(@BindingParam("idToUpdate") int idToUpdate) {
//		toUpdate = true;
//		rowUpdate = true;
//		Usuarios userToUpdate = usuariosRepository.findByIdUsuario(idToUpdate);
//		currentUsuarios = userToUpdate;
//	}

	@NotifyChange({ "listUsuario", "currentUsuarios" })
	public void refreshRowTemplate(Usuarios currentUsuarios) {
		// replace the element in the collection by itself to trigger a model update
		listUsuario.set(listUsuario.indexOf(currentUsuarios), currentUsuarios);
	}

	@NotifyChange({ "rowUpdate", "toUpdate", "currentUsuarios" })
	@Command
	public void changeToUpdate(@BindingParam("currentUsuarios") Usuarios upUsuarios) {
		this.currentUsuarios = upUsuarios;
		refreshRowTemplate(currentUsuarios);
		currentUsuarios = new Usuarios();
		rowUpdate = false;
		toUpdate = false;
	}

	public boolean validateInsert() {
		if (!currentUsuarios.getNombreCompleto().isEmpty() && !currentUsuarios.getContrasena().isEmpty()
				&& !currentUsuarios.getUsuario().isEmpty()) {
			return true;
		}
		return false;
	}

	public List<Usuarios> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<Usuarios> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public boolean isAddUsuario() {
		return addUsuario;
	}

	public void setAddUsuario(boolean addUsuario) {
		this.addUsuario = addUsuario;
	}

	public boolean isToUpdate() {
		return toUpdate;
	}

	@NotifyChange({ "listUsuario", "toUpdate" })
	public void setToUpdate(boolean toUpdate) {
		this.toUpdate = toUpdate;
	}

	public boolean isRowUpdate() {
		return rowUpdate;
	}

	@NotifyChange({ "rowUpdate" })
	public void setRowUpdate(boolean rowUpdate) {
		this.rowUpdate = rowUpdate;
	}

	public Usuarios getCurrentUsuarios() {
		return currentUsuarios;
	}

	public void setCurrentUsuarios(Usuarios currentUsuarios) {
		this.currentUsuarios = currentUsuarios;
	}

}
