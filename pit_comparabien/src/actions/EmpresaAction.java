package actions;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import beans.DepartamentoDTO;
import beans.EmpresaDTO;
import services.EmpresaService;
import utils.UtilFiles;

@ParentPackage("pit")
public class EmpresaAction extends ActionSupport {

	EmpresaService servEmpresa = new EmpresaService();

	private static final long serialVersionUID = -4293793472548712211L;
	private Map<String, Object> session = (Map<String, Object>) ActionContext.getContext().getSession();
	private Map<String, Object> response = new HashMap<String, Object>();
	private EmpresaDTO empresa;
	String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Action(value = "/listaEmpresas", results = { @Result(name = "listado", location = "/main/crudEmpresas.jsp")})
	public String listaEmpresas() {
		List<EmpresaDTO> listado = servEmpresa.listaEmpresas();
		if (listado == null) {
			response.put("ver", true);
			response.put("cantidad", 0);
		} else {
			response.put("empresas", listado);
			response.put("cantidad", listado.size());
		}
		return "listado";
	}
	
	@Action(value="/regEmpresa", results= {
			@Result(name="registrar", location="/main/regEmpresa.jsp")
	})
	public String regEmpresa() {
		List<DepartamentoDTO> listado = servEmpresa.listaDepartamentos();
		response.put("departamentos", listado);
		return "registrar";
	}
	
	@Action(value="/submitEmpresa",results= {
			@Result(name="registra" ,type="json")
	})
	public String submitEmpresa() {
		if( empresa != null ) {
			mensaje = servEmpresa.regEmpresa(empresa);				
			return "registra";
//			if( logo != null && logo.length() > 0 ) {
//				try {
//					String file = new UtilFiles().subirLogo(logo);
//					empresa.setLogo(file);
//					mensaje = servEmpresa.regEmpresa(empresa);				
//					return "registra";
//				} catch (IOException e) {
//					mensaje = e.getCause().toString();				
//					return "registra";
//				}				
//			}else {
//				mensaje = "No ha seleccionado una imagen";
//				return "registra";
//			}
		}
		mensaje = "ingrese los campos requeridos";
		return "registra";
	}
	

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	

}
