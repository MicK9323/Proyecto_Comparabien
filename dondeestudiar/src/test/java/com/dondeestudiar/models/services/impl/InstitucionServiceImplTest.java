package com.dondeestudiar.models.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.models.services.IInstitucionesService;
import com.dondeestudiar.validaciones.validaciones;

import static org.junit.Assert.*;

import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstitucionServiceImplTest {

	@Autowired
	IInstitucionesService institucionesService;

	@Rollback(true)
	@Test
	@Transactional
	public void SavingInstitucionWithValidData() {
		Parametros tipoIstitucion = new Parametros();
		tipoIstitucion.setIdParam("TC001");

		Parametros tipoGestion = new Parametros();
		tipoGestion.setIdParam("TG002");

		Institucion obj = new Institucion();

		obj.setRuc("10729273321");
		obj.setNombre("Universidad Cesar Vallejo");
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
		assertTrue(institucionesService.SaveAndVerify(obj));
	}

	@Rollback(true)
	@Test
	@Transactional
	public void SavingInstitucionWithInvalidData() {
		assertFalse(validaciones.SavingInstitucionWithInvalidData());
	}

}