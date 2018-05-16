package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/sedes")
@SessionAttributes(names = {"sede"})
public class SedesController {

    @Autowired
    ISedeService sedeService;

    @ModelAttribute
    public Sede setSede(){
        return new Sede();
    }

    //    Ir a vista ver sede
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable String id, Map<String, Object> model, HttpServletRequest request) {
//        if( !validarSesion(request) ){
//            model.put("error","Inicie sesión antes de continuar");
//            return "redirect:/admin/login";
//        }
        Sede sede = sedeService.buscarSede(Integer.parseInt(id));
        model.put("titulo","Detalle de Sede");
        model.put("sede",sede);
        return "admin/editSede";
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
