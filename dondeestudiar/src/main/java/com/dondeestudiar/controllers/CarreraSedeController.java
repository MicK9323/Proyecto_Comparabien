package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.CarreraSede;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.ICarreraSedeService;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.ISedeService;
import com.dondeestudiar.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    ISedeService sedeService;

    @Autowired
    IInstitucionesService institucionesService;

    @Autowired
    ICarreraSedeService carreraSedeService;

    // Carreras por Institución
    @GetMapping(value = "/mostrar/{id}")
    public String MostrarCarreras(@PathVariable String id, HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if( !validarSession(request) ){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Institucion institucion = institucionesService.findById(Integer.parseInt(id));
        List<Sede> sedes = institucion.getSedes();
        List<Carrera> carreras = carreraService.sp_carrerasInstitucion(Integer.parseInt(id));
        if(carreras.isEmpty())
            model.put("info","No hay carreras registradas en esta institución");
        model.put("carreras",carreras);
        model.put("institucion",institucion);
        model.put("sedes",sedes);
        model.put("titulo","Carreras - "+institucion.getNombre());
        return "admin/carrerasInstitucion";
    }

    @GetMapping(value = "/sede/{id}/mostrar")
    public String CarrerasPorSede(@PathVariable String id, HttpServletRequest request, Map<String, Object> model,
                                  RedirectAttributes flash){
        if(!validarSession(request)){
            flash.addFlashAttribute("error",Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        Sede sede = sedeService.buscarSede(Integer.parseInt(id));
        List<CarreraSede> carreras = carreraSedeService.sp_carrerasSede(Integer.parseInt(id));
        if(!carreras.isEmpty())
            model.put("lista",carreras);
        else
            model.put("vacio","No hay carreras registradas en esta sede");
        model.put("sede",sede);
        model.put("titulo","Carreras -"+sede.getInstitucion().getNombre());
        return "admin/verCarrerasSede";
    }

    /*@GetMapping(value = "/refresh/{sede}")
    public String RefreshTable( @PathVariable String sede, Map<String, Object> model, HttpServletRequest request ){
        List<CarreraSede> carreras = carreraSedeService.sp_carrerasSede(Integer.parseInt(sede));
        if(!carreras.isEmpty())
            model.put("lista",carreras);
        else
            model.put("vacio","No hay carreras registradas en esta sede");
        return "admin/carrerasInstitucion :: #refreshTable";
    }*/

    //    Validar Session
    public boolean validarSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("usuario") == null) {
            return false;
        } else {
            return true;
        }
    }
}
