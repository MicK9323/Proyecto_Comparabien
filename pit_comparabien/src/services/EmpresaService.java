package services;

import java.util.List;
import java.util.Map;

import beans.DepartamentoDTO;
import beans.EmpresaDTO;
import dao.DAOFactory;
import interfaces.EmpresaDAO;
import utils.Utilitario;

public class EmpresaService {
	
	DAOFactory origen = DAOFactory.getDAOFactory(Utilitario.Constantes.ORIGEN_DATOS);
	EmpresaDAO daoEmpresa = origen.getEmpresaDAO();
	
	public List<EmpresaDTO> listaEmpresas(){
		return daoEmpresa.listaEmpresas();
	}
	
	public String regEmpresa(EmpresaDTO emp){
		return daoEmpresa.regEmpresa(emp);
	}
	
	public List<DepartamentoDTO> listaDepartamentos(){
		return daoEmpresa.listaDepartamentos();
	}

}
