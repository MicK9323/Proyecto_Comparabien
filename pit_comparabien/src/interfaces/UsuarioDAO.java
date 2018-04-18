package interfaces;

import java.util.Map;

import beans.EmpresaDTO;


public interface UsuarioDAO {
	
	public Map<String, Object> loginUsuario(String usuario, String clave);
	
	public Map<String, Object> enlaces(int rol);
	
}
