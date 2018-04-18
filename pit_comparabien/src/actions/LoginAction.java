package actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import beans.EnlaceDTO;
import beans.UsuarioDTO;
import services.LoginService;

@ParentPackage("pit")
public class LoginAction extends ActionSupport {
	
	LoginService loginServ = new LoginService();

	private static final long serialVersionUID = 1797470979300867219L;
//	Session
	private Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
	
	private Map<String, Object> response = new HashMap<String, Object>();
	private String usuario, clave;
	private String mensaje;	
	
	@SuppressWarnings("unchecked")
	@Action(value="/login", results= {
			@Result(name="loged",location="/main/main-menu.jsp"),
			@Result(name="error",location="/main/login.jsp")
	})
	public String login() {
		response = loginServ.loginUser(	usuario, clave);
		UsuarioDTO user = (UsuarioDTO) response.get("logedUser");		
		if(user == null) {
			mensaje = (String) response.get("error");
			response.put("mostrar", true);
			response.put("msg", mensaje);
			return "error";
		}else {
			Map<String, Object> enlaces = loginServ.enlaces(user.getId_rol());
			List<EnlaceDTO> menuEmpresa = (List<EnlaceDTO>) enlaces.get("0");
			List<EnlaceDTO> menuTarjetas = (List<EnlaceDTO>) enlaces.get("1");
			List<EnlaceDTO> menuAhorros = (List<EnlaceDTO>) enlaces.get("2");
			List<EnlaceDTO> menuCreditos = (List<EnlaceDTO>) enlaces.get("3");
			List<EnlaceDTO> menuSeguros = (List<EnlaceDTO>) enlaces.get("4");
			List<EnlaceDTO> menuCom = (List<EnlaceDTO>) enlaces.get("5");
			List<EnlaceDTO> menuAdmin = (List<EnlaceDTO>) enlaces.get("6");
			session.put("usuario", user);
			session.put("opcionesEmpresa", menuEmpresa);
			session.put("opcionesTarjetas", menuTarjetas);
			session.put("opcionesAhorros", menuAhorros);
			session.put("opcionesCreditos", menuCreditos);
			session.put("opcionesSeguros", menuSeguros);
			session.put("opcionesComu", menuCom);
			session.put("opcionesAdmin", menuAdmin);
			return "loged";
		}
	}	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Map<String, Object> getResponse() {
		return response;
	}
	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	

}
