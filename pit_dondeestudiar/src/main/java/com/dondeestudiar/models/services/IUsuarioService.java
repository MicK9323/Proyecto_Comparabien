package com.dondeestudiar.models.services;

import java.util.Map;



public interface IUsuarioService {
	
	public Map<String, Object> login(String usuario, String clave);
	
}
