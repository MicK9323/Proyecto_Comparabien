package com.dondeestudiar.models.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.entities.Ubigueo;
import com.dondeestudiar.models.services.ISedeService;
import com.dondeestudiar.validaciones.validaciones;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SedeServiceImplTest {

	@Autowired
	ISedeService sedeService;

	@Rollback(true)
	@Test
	@Transactional
	public void SavingSedeWithValidData() {

		Sede obj = new Sede();
		Institucion institucion = new Institucion();
		Ubigueo ubicacion = new Ubigueo();

		institucion.setId(1);
		ubicacion.setCodUbigueo("010109");

		obj.setNomSede("Sede Lima Norte");
		obj.setInstitucion(institucion);
		obj.setCx("40.7127837");
		obj.setCy("-74.00594130000002");
		obj.setDireccion("New York, NY, USA");
		obj.setEstado(true);
		obj.setTelf("53540478");
		obj.setUbicacion(ubicacion);
		assertTrue(sedeService.SaveAndVerify(obj));
	}

	@Rollback(true)
	@Test
	@Transactional
	public void SavingSedeWithInvalidData() {
		assertFalse(validaciones.SavingSedeWithInvalidData());
	}

}