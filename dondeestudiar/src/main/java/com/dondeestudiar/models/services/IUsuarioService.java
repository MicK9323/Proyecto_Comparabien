package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
	
	Usuario login(String usuario, String clave);

	void mergeUsuario(Usuario user);

	List<Usuario> listAll();
	
}
