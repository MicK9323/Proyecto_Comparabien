package interfaces;

import java.util.Map;

import beans.UsuarioDTO;

public interface UsuarioDAO {
	
	public Map<String, Object> loginUsuario(String usuario, String clave);
	
}
