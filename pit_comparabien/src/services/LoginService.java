package services;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;

import dao.DAOFactory;
import interfaces.UsuarioDAO;
import utils.Utilitario;

@ParentPackage("pit")
public class LoginService {
	
	DAOFactory origen = DAOFactory.getDAOFactory(Utilitario.Constantes.ORIGEN_DATOS);
	UsuarioDAO daoUser = origen.getUsuarioDAO();
	
	public Map<String, Object> loginUser(String usuario, String clave) {
		return daoUser.loginUsuario(usuario, clave);
	}
	
	public Map<String, Object> enlaces(int rol){
		return daoUser.enlaces(rol);
	}
	
}
