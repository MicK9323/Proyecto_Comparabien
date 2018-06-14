package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/carreras")
public class CarreraSedeController {
    @Autowired
    ICarreraService carreraService;

    @Autowired
    IInstitucionesService institucionesService;

    // Carreras por Institución
    @GetMapping(value = "/mostrar/{id}")
    public String MostrarCarreras(@PathVariable String id, HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if( !validarSession(request) ){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findById(Integer.parseInt(id));
        List<Carrera> carreras = carreraService.sp_carrerasInstitucion(Integer.parseInt(id));
        model.put("carreras",carreras);
        model.put("institucion",institucion);
        model.put("titulo","Carreras - "+institucion.getNombre());
        return "admin/carrerasInstitucion";
    }


    //    Validar Session
    public boolean validarSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("usuario") == null) {
            return false;
        } else {
            return true;
        }
    }
}
