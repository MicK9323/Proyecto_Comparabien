package actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
	
	@Action(value="/login", results= {
			@Result(name="loged",location="/main/index.jsp"),
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
			session.put("usuario", user);
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
