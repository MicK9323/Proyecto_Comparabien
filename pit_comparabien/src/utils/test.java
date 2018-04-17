package utils;

import java.util.Map;

import beans.UsuarioDTO;
import services.LoginService;

public class test {

	public static void main(String[] args) {
		
		LoginService serv = new LoginService();
		
		Map<String, Object> map = serv.loginUser("70417573", "70417573");
		
		if(!(map.get("logedUser")==null)) {
			UsuarioDTO usuario = (UsuarioDTO) map.get("logedUser");
			System.out.println(String.format("Usuario %s logeado", usuario.getNom_user()));
		}else {
			String error = (String) map.get("error");
			System.out.println("Error: "+error);
		}
		
		

	}

}
