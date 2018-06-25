package com.dondeestudiar.controllers;

import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.services.IUsuarioService;
import com.dondeestudiar.utils.Constantes;
import com.dondeestudiar.utils.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/usuarios")
@SessionAttributes(names = {"usuario"})
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    // Lista de usuarios
    @GetMapping(value = "/lista")
    public String ListUsuarios(HttpServletRequest request, Map<String,Object> model, RedirectAttributes flash){
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }
        List<Usuario> usuarios = usuarioService.listAll();
        model.put("titulo","Listado de Usuarios");
        model.put("usuarios",usuarios);
        return "admin/listaUsuarios";
    }

    // Ver perfil de usuario
    @GetMapping(value = "/perfil")
    public String VerPerfil(HttpServletRequest request, Map<String, Object> model, RedirectAttributes flash) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        model.put("usuario", usuario);
        model.put("titulo", "Perfil de Usuario");
        return "admin/userProfile";
    }

    // Actualizar datos de usuario
    @PostMapping(value = "/actualizar")
    public String ActualizarUsuario(@Valid Usuario usuario, BindingResult result, SessionStatus status,
            Map<String, Object> model, HttpServletRequest request, RedirectAttributes flash, MultipartFile file) {
        if (!validarSesion(request)) {
            flash.addFlashAttribute("error", Constantes.SESSION_EXPIRED);
            return "redirect:/admin/login";
        }

        if(result.hasErrors()){
            model.put("error",Constantes.INVALID_DATA);
            return "admin/userProfile";
        }

        if(!(file.isEmpty() || file == null)){
            try{
                if(!(file.getSize() > Constantes.MAX_UPLOAD_SIZE)){
                    String imagen = "";
                    try{
                        if(usuario.getFoto().isEmpty()){
                            imagen = new UploadFiles().subirImagenFTP(file,Constantes.UPLOADS_USUARIOS);
                        }else{
                            if( new UploadFiles().eliminarImagenFTP(Constantes.UPLOADS_USUARIOS,usuario.getFoto()) ){
                                imagen = new UploadFiles().subirImagenFTP(file,Constantes.UPLOADS_USUARIOS);
                            } //Agregar error si no se puede sobreescribir el fichero
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        model.put("error",e.getCause().toString()+": "+e.getMessage());
                    }
                    usuario.setRutaFoto(Constantes.URL_ENDPOINT+Constantes.UPLOADS_USUARIOS+imagen);
                    usuario.setFoto(imagen);
                }else{
                    model.put("error",Constantes.FILE_SIZE);
                    return "admin/userProfile";
                }
            }catch(Exception e){
                e.printStackTrace();
                model.put("error",Constantes.FILE_SIZE);
                return "admin/userProfile";
            }
        }
        usuarioService.mergeUsuario(usuario);
        flash.addFlashAttribute("info",Constantes.PROLILE_UPDATED);
        status.setComplete();
        return "redirect:/admin/login";
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
