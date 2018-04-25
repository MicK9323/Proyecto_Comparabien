package com.dondeestudiar.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/login/{usuario}/{clave}")
	public Map<String, Object> login( @PathVariable String usuario, @PathVariable String clave ) {
		return service.login(usuario, clave);
	}
	

}
