package com.dondeestudiar.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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
@SessionAttributes(names = { "institucion" })
public class InstitucionesController {

    protected final Log logger = LogFactory.getLog(this.getClass());

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

	// Cargar foto de institucion
	@GetMapping(value = "/cargarFoto/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename, RedirectAttributes flash) {
		Resource recurso = null;
		try {
			if (filename.equals(Constantes.NOT_FOUND)) {
				recurso = new UploadFiles().cargarImagen(filename, Constantes.FILE_ERROR);
			} else {
				recurso = new UploadFiles().cargarImagen(filename, Constantes.UPLOADS_INSTITUCIONES);
			}
		} catch (MalformedURLException e) {
			flash.addAttribute("Error ", e.getClass() + " " + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	// Buscar institucion por nombre
	@PostMapping(value = "/buscar")
	public String buscar(@RequestParam String nombre, Map<String, Object> model) {
		List<Institucion> listado = institucionesService.findByNombre("%" + nombre + "%");
		if (!listado.isEmpty()) {
			model.put("listado", listado);
		} else {
			model.put("info", "No se encontraron coincidencias");
		}
		model.put("mostrar", true);
		return "admin/listaInstituciones";
	}

	// Autocompletar ubigueos
	@GetMapping(value = "/cargar-ubigueo/{term}")
	public String cargarUbigueo(@PathVariable String term, Map<String, Object> model) {
		term = term.replaceAll("_", " ");
		List<Ubigueo> ubigueos = ubigueoService.execSpUbigueo(term);
		model.put("ubigueos", ubigueos);
		return "admin/regInstitucion :: #listaUbigueos";
	}

	// Listado de instituciones
	@GetMapping(value = "/listar")
	public String listar(Map<String, Object> model, HttpServletRequest request, RedirectAttributes flash) {
		if (validarSesion(request) == false) {
			flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		}
		List<Institucion> listado = institucionesService.listarInstituciones();
		List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
		model.put("listado", listado);
		model.put("parametros", tipoInstitucion);
		model.put("titulo", "Listado de Instituciones");
		return "admin/listaInstituciones";
	}

	// Ver detalle de institucion
	@GetMapping(value = "/ver/{ruc}")
	public String verInstitucion(@PathVariable String ruc, Map<String, Object> model, HttpServletRequest request,
			RedirectAttributes flash) {
		if (validarSesion(request) == false) {
			flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		}
		Institucion institucion = institucionesService.findByRuc(ruc);
		model.put("titulo", "Detalle Institucion");
		model.put("institucion", institucion);
		return "admin/verInstitucion";
	}

	// LLamar a vista de registrar Institucion
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/nuevo")
	public String nuevo(HttpServletRequest request, Map<String, Object> model) {
		if (request.getSession().getAttribute("usuario") == null) {
			model.put("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		} else {
			if (request.getSession().getAttribute("sedes") != null) {
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

	// Registrar una Institucion y sus sedes
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/nuevo")
	public String registrar(@Valid Institucion institucion, BindingResult result, SessionStatus status, HttpServletRequest request,
			HttpSession session, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes flash) {

	    if( !validarSesion(request) ){
            model.addAttribute("error", "Inicie sesion antes de continuar");
            return "redirect:/admin/login";
        }

        List<Parametros> tipoInstitucion = parametrosService.findByIdGrupo(Constantes.TIPO_INSTITUCION);
        List<Parametros> tipoGestion = parametrosService.findByIdGrupo(Constantes.TIPO_GESTION);
        model.addAttribute("tipoInstitucion", tipoInstitucion);
        model.addAttribute("tipoGestion", tipoGestion);
        model.addAttribute("listaSedes",(ArrayList<Sede>)session.getAttribute("sedes"));
        model.addAttribute("titulo", "Nueva Institución");

		if (result.hasErrors()) {
			return "admin/regInstitucion";
		}

		if (session.getAttribute("sedes") == null) {
			model.addAttribute("warning", "No ha ingresado sedes para la institución");
			return "admin/regInstitucion";
		}

		if (file.isEmpty() || file.getName().equals("")) {
			model.addAttribute("warning", "Debe seleccionar una imagen");
			return "admin/regInstitucion";
		}

		String foto = "";
		if (!file.isEmpty()) {
			try {
				foto = new UploadFiles().subirFoto(file, Constantes.UPLOADS_INSTITUCIONES);
				institucion.setLogo(foto);
				Institucion persistObj = institucionesService.addInstitucion(institucion);
				if (persistObj != null) {
					ArrayList<Sede> sedes = (ArrayList<Sede>) session.getAttribute("sedes");
					for (Sede obj : sedes) {
						obj.setInstitucion(persistObj);
					}
					institucionesService.addSedes(sedes);
				}
				session.removeAttribute("sedes");
				flash.addFlashAttribute("success", "Institucion Registrada");
			} catch (IOException e) {
				flash.addFlashAttribute("error", e.getClass() + " " + e.getCause().toString());
				e.printStackTrace();
				return "admin/regInstitucion";
			}
		}
		status.setComplete();
		return "redirect:/instituciones/listar";
	}

	// Listar Sedes en la vista de registra antes de guardarlas en base de datos
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/cargar-sedes/{data}")
	public String cargarSedes(@PathVariable String data, Map<String, Object> model, HttpServletRequest request) {
		List<Sede> sedes = null;
		String[] array = new G().formarArray(data, ",");
		System.out.println(array);
		Sede sede = new Sede();
		if (request.getSession().getAttribute("sedes") == null) {
			sedes = new ArrayList<Sede>();
			request.getSession().setAttribute("sedes", sedes);
		} else {
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

	// Llamar a vista Editar Institucion
	@GetMapping(value = "/editar/{ruc}")
	public String editarInstitucion(@PathVariable String ruc, Map<String, Object> model, HttpServletRequest request) {
		if (validarSesion(request) == false) {
			model.put("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		}
		Institucion institucion = institucionesService.findByRuc(ruc);
		model.put("titulo", "Editar Institucion");
		model.put("institucion", institucion);
		return "admin/editInstitucion";
	}

	// Editar institucion
	@PostMapping(value = "/editar")
	public String mergeInstitucion(@Valid Institucion institucion, BindingResult result, HttpServletRequest request,
			SessionStatus status, @RequestParam("file") MultipartFile file, Map<String, Object> model,
			RedirectAttributes flash) {
		if (validarSesion(request) == false) {
			flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
			return "redirect:/admin/login";
		}

		if (result.hasErrors()) {
			model.put("titulo", "Nueva Institución");
			return "admin/editInstitucion";
		}

		logger.info("NOmbre de archivo: "+file.getOriginalFilename());
        logger.info("Logo de objeto: "+institucion.getLogo());

		if ( !file.isEmpty() ) {
		    logger.info("Entra a cambiar imagen");
		    logger.info(!file.isEmpty() || file != null);
			String foto = "";
			try {
				foto = new UploadFiles().subirFoto(file, Constantes.UPLOADS_INSTITUCIONES);
			} catch (IOException e) {
				e.printStackTrace();
			}
			institucion.setLogo(foto);
		}

		institucionesService.saveInstitucion(institucion);
		String ruc = institucion.getRuc();
		status.setComplete();
		return "redirect:/instituciones/ver/" + ruc;
	}
	
//	Deshabilitar institucion
	@GetMapping(value="/disabled/{id}")
	public String disabled( @PathVariable String id, Map<String, Object> model ) {
		int ID = Integer.parseInt(id);
		institucionesService.disabledInstitucion(ID);
		model.put("success", "Institucion deshabilitada");
		return "redirect:/instituciones/listar";
	}

//	Habilitar institucion
	@GetMapping(value="/enabled/{id}")
	public String enabled( @PathVariable String id, Map<String, Object> model ) {
		int ID = Integer.parseInt(id);
		institucionesService.enabledInstitucion(ID);
		model.put("success", "Institucion habilitada");
		return "redirect:/instituciones/listar";
	}
	
	// Validar si existe sesion
	private boolean validarSesion(HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			return false;
		} else {
			return true;
		}
	}

}
