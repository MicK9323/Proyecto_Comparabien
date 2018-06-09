package com.dondeestudiar.validaciones;


import org.springframework.beans.factory.annotation.Autowired;
import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.models.services.ISedeService;

public class validaciones {
	
	  @Autowired static
	    ICarreraService carreraService;
	  @Autowired static
		IInstitucionesService institucionesService;
		@Autowired static 
		ISedeService sedeService;

	  
	public static boolean SavingCarreraWithInvalidData(){
        try {
        	Area area = new Area();
            area.setId(23);
            
            Parametros tipoCarrera = new Parametros();
            tipoCarrera.setIdParam("TC123");

            Carrera obj = new Carrera();
            obj.setNombre("Es7023tologia");
            obj.setArea(area);
            obj.setTipoCarrera(tipoCarrera);
            obj.setDuracion(8);
            obj.setPopularidad(1);
            obj.setRemuneracion(950);
            obj.setRutaImagen("Test");
            obj.setImagen("Test");
            obj.setEstado(true);
           carreraService.SaveAndVerify(obj);
           return true;
		} catch (Exception e) {
			return false;
		}
    }
	
	public static boolean SavingInstitucionWithInvalidData() {
		try {
			Parametros tipoIstitucion = new Parametros();
			tipoIstitucion.setIdParam("TC001");
			
			Parametros tipoGestion = new Parametros();
			tipoGestion.setIdParam("TG002");
			
			Institucion obj = new Institucion();

			obj.setRuc("10729273321");
			obj.setNombre("Universidad Cesar Vallejo222");
			obj.setTipoInstitucion(tipoIstitucion);
			obj.setTipoGestion(tipoGestion);
			obj.setTelf("5354074");
			obj.setWebsite("https://www.ucv.edu.pe");
			obj.setPopularidad(1);
			obj.setAdmisiones(1);
			obj.setResidencial(true);
			obj.setRutaLogo("rutalogo");
			obj.setLogo("logo");
			obj.setEstado(true);
			institucionesService.SaveAndVerify(obj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		
	
	
	public static boolean SavingSedeWithInvalidData() {
		try {
			Sede obj = new Sede();
			Institucion institucion = new Institucion();
			Ubigueo ubicacion = new Ubigueo();
			
			institucion.setId(1);
			ubicacion.setCodUbigueo("010109");
			
			obj.setNomSede("Sede Lim444a Norte");
			obj.setInstitucion(institucion);
			obj.setCx("40.7127837");
			obj.setCy("-74.00594130000002");
			obj.setDireccion("New York, NY, USA");
			obj.setEstado(true);
			obj.setTelf("53540478");
			obj.setUbicacion(ubicacion);
			sedeService.SaveAndVerify(obj);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
