package com.dondeestudiar.models.interfaces;

import java.util.Map;



public interface IUsuarioDAOCustom {
	public Map<String, Object> login(String usuario, String clave);
}
