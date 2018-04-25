package com.dondeestudiar.models.services.impl;


import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dondeestudiar.models.interfaces.IUsuarioDAOCustom;
import com.dondeestudiar.models.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDAOCustom usuarioRepository;
	
	@Override
	public Map<String, Object> login(String usuario, String clave) {
		return usuarioRepository.login(usuario, clave);
	}
	
	
	
}
