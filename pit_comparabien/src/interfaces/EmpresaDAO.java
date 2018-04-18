package interfaces;

import java.util.List;
import java.util.Map;

import beans.DepartamentoDTO;
import beans.EmpresaDTO;

public interface EmpresaDAO {

	public Map<String, Object> regEmpresa(EmpresaDTO emp);
	public List<EmpresaDTO> listaEmpresas();
	public List<DepartamentoDTO> listaDepartamentos();
	
}
