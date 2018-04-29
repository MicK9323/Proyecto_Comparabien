package com.dondeestudiar.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.IParametrosService;
import com.dondeestudiar.models.services.IUbigueoService;
import com.dondeestudiar.utils.Constantes;
import com.dondeestudiar.utils.G;
import com.dondeestudiar.utils.UploadFiles;

@Controller
@RequestMapping("/instituciones")
public class InstitucionesController {

	@Autowired
	IParametrosService parametrosService;

	@Autowired
	IUbigueoService ubigueoService;
	
	@Autowired
	IInstitucionesService institucionesService;

	@ModelAttribute("institucion")
	public Institucion setInstitucion() {
		return new Institucion();
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/nuevo")
	public String nuevo(HttpServletRequest request, Map<String, Object> model) {
		if (request.getSession().getAttribute("usuario") == null) {
			model.put("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		} else {
			if( request.getSession().getAttribute("sedes") != null ) {
				ArrayList<Sede> sedes = (ArrayList<Sede>) request.getSession().getAttribute("sedes");
				model.put("listaSedes", sedes);
			}
			List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
			List<Parametros> tipoGestion = parametrosService.findByIdGrupo(Constantes.TIPO_GESTION);
			model.put("tipoInstitucion", tipoInstitucion);
			model.put("tipoGestion", tipoGestion);
			model.put("titulo", "Nueva Institución");
			return "admin/regInstitucion";
		}
	}
		
	
	@SuppressWarnings("unchecked")
	@PostMapping(value="/nuevo")
	public String registrar(@ModelAttribute("institucion") Institucion institucion, HttpSession session,
			@RequestParam("file") MultipartFile file, RedirectAttributes flash) {
		String foto = "";
		if( !file.isEmpty() ) {
			try {
				foto = new UploadFiles().subirFoto(file);
				institucion.setLogo(foto);
				Institucion persistObj = institucionesService.addInstitucion(institucion);
				if( persistObj != null ) {
					ArrayList<Sede> sedes = (ArrayList<Sede>) session.getAttribute("sedes");
					for(Sede obj: sedes) {
						obj.setInstitucion(persistObj);
					}
					institucionesService.addSedes(sedes);
				}
				session.removeAttribute("sedes");
				flash.addFlashAttribute("success", "Institucion Registrada");
			} catch (IOException e) {
				flash.addFlashAttribute("error", e.getClass()+" "+e.getCause().toString());
				e.printStackTrace();
				return "admin/regInstitucion";
			}
		}
		return "admin/main";
	}
	
	
	@GetMapping(value = "/cargar-ubigueo/{term}")
	public String cargarUbigueo(@PathVariable String term, Map<String, Object> model) {
		List<Ubigueo> ubigueos = ubigueoService.execSpUbigueo(term);
		model.put("ubigueos", ubigueos);
		return "admin/regInstitucion :: #listaUbigueos";
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/cargar-sedes/{data}")
	public String cargarSedes(@PathVariable String data,
			Map<String, Object> model, HttpServletRequest request) {
		List<Sede> sedes = null;
		String[] array = new G().formarArray(data, ",");
		Sede sede = new Sede();
		if( request.getSession().getAttribute("sedes") == null ) {
			sedes = new ArrayList<Sede>();
			request.getSession().setAttribute("sedes", sedes);
		}else {
			System.out.println("Llega session cargada");
			sedes = (ArrayList<Sede>) request.getSession().getAttribute("sedes");
		}
		sede.setNomSede(array[0].replaceAll("_", " ").trim());
		sede.setUbicacion(ubigueoService.findByCodUbigueo(array[1]));
		sede.setDireccion(array[2].replaceAll("_", " ").trim());
		sede.setTelf(array[3].replaceAll("_", " ").trim());
		sede.setCx(array[4].trim());
		sede.setCy(array[5].trim());
		sedes.add(sede);
		model.put("listaSedes", sedes);
		return "admin/regInstitucion :: #detSedes";
	}

}
