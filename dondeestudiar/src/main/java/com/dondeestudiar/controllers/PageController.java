package com.dondeestudiar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping(value= {"","/index"})
	public String index() {
		return "Inicio";
	}
	
	
	@GetMapping(value="/preguntas")
	public String preguntas() {
		return "Preguntas";
	}
	
	@GetMapping(value="/filtro")
	public String filtro() {
		return "Filtro";
	}
	
//	Vista Parcial Busqueda Avanzada
	@GetMapping(value="/loadBusquedaAvanzada")
	public String busquedaAvanzada() {
		return "busquedaAvanzada";
	}
	
//	Vista Parcial Busqueda Basica
	@GetMapping(value="/loadBusquedaBasica")
	public String busquedaBasica() {
		return "busquedaBasica";
	}
	
}
