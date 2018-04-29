package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Usuario;

public interface IUsuarioService {
	
	public Usuario login(String usuario, String clave);
	
}
