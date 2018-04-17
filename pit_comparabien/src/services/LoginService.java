package services;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;

import dao.DAOFactory;
import interfaces.UsuarioDAO;
import utils.Constantes;

@ParentPackage("pit")
public class LoginService {
	
	DAOFactory origen = DAOFactory.getDAOFactory(Constantes.ORIGEN_DATOS);
	UsuarioDAO daoUser = origen.getUsuarioDAO();
	
	public Map<String, Object> loginUser(String usuario, String clave) {
		return daoUser.loginUsuario(usuario, clave);
	}
	
}
