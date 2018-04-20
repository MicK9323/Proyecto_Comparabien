package interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import beans.DepartamentoDTO;
import beans.EmpresaDTO;

public interface EmpresaDAO {

	public String regEmpresa(EmpresaDTO emp);
	public List<EmpresaDTO> listaEmpresas();
	public List<DepartamentoDTO> listaDepartamentos();
	public EmpresaDTO buscarEmpresa(String codigo);
	public String uptEmpresa(EmpresaDTO emp);
	
}
