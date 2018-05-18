package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/sedes")
@SessionAttributes(names = {"sede"})
public class SedesController {

    @Autowired
    ISedeService sedeService;

    @ModelAttribute
    public Sede setSede() {
        return new Sede();
    }

    //    Ir a vista ver sede
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable String id, Map<String, Object> model, HttpServletRequest request, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", "Inicie sesión antes de continuar");
            return "redirect:/admin/login";
        }
        Sede sede = sedeService.buscarSede(Integer.parseInt(id));
        model.put("titulo", "Detalle de Sede");
        model.put("sede", sede);
        return "admin/editSede";
    }

    //Editar Sede
    @PostMapping(value = "/editar")
    public String editar(@Valid Sede sede, BindingResult result, HttpServletRequest request,
                         Map<String, Object> model, SessionStatus status, RedirectAttributes flash) {

        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", "Inicie sesion antes de continuar");
            return "redirect:/admin/login";
        }

        if (result.hasErrors()) {
            model.put("titulo", "Detalle de Sede");
            return "admin/editSede";
        }

        int id = sede.getId();

        if (sede.getId() >= 1) {
            sedeService.saveSede(sede);
            status.setComplete();
            flash.addFlashAttribute("success", "Se editaron los datos de la sede correctamente");
            return "redirect:/sedes/ver/" + id;
        } else {
            status.setComplete();
            flash.addFlashAttribute("error", "Ocurrió un error al cargar los datos de la sede");
            return "redirect:/sedes/ver/" + id;
        }
    }

    //    Desabilitar sede
    @GetMapping(value = "/disabled/{id}/{ruc}")
    public String disabledSede(@PathVariable String id, @PathVariable String ruc, RedirectAttributes flash) {
        int idSede = Integer.parseInt(id);
        sedeService.disabledSede(idSede);
        flash.addFlashAttribute("success", "Sede Deshabilitada");
        return "redirect:/instituciones/ver/" + ruc;
    }

    //    Habilitar Sede
    @GetMapping(value = "/enabled/{id}/{ruc}")
    public String enabledSede(@PathVariable String id, @PathVariable String ruc, RedirectAttributes flash) {
        int idSede = Integer.parseInt(id);
        sedeService.enabledSede(idSede);
        flash.addFlashAttribute("success", "Sede Habilitada");
        return "redirect:/instituciones/ver/" + ruc;
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
