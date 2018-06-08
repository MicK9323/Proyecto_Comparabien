package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.services.ICarreraService;
import com.dondeestudiar.validaciones.validaciones;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarreraServiceImplTest {

	@Autowired
	ICarreraService carreraService;

	@Rollback(true)
	@Test
	@Transactional
	public void SavingCarreraWithValidData() {
		Area area = new Area();
		area.setId(2);

		Parametros tipoCarrera = new Parametros();
		tipoCarrera.setIdParam("TC002");

		Carrera obj = new Carrera();
		obj.setNombre("Estomatologia");
		obj.setArea(area);
		obj.setTipoCarrera(tipoCarrera);
		obj.setDuracion(8);
		obj.setPopularidad(1);
		obj.setRemuneracion(950);
		obj.setRutaImagen("Test");
		obj.setImagen("Test");
		obj.setEstado(true);
		assertTrue(carreraService.SaveAndVerify(obj));
	}

	@Rollback(true)
	@Test
	@Transactional
	public void SavingCarreraWithInvalidData() {
		assertFalse(validaciones.SavingCarreraWithInvalidData());
	}

}