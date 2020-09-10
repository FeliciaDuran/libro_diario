package mx.com.felicia.libro_diario.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import mx.com.felicia.libro_diario.dal.models.Empresa;
import mx.com.felicia.libro_diario.dal.repositories.EmpresaRepository;

public class EmpresaVM {
	
	@WireVariable
	private EmpresaRepository empresaRepository;
	@Wire
	private List<Empresa> empresaList = new ArrayList<>();
	
	@Init
	public void init() {
		empresaList = empresaRepository.findAll();
	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

}
