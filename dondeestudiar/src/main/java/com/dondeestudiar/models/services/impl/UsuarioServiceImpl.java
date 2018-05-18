package com.dondeestudiar.models.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dondeestudiar.models.dao.IUsuarioDAO;
import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	IUsuarioDAO usuarioDAO;

	@Override
	@Transactional(readOnly = true)
	public Usuario login(String usuario, String clave) {
		Usuario user = null;
		try {
			user = usuarioDAO.login(usuario, clave);
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	
	
	
}
