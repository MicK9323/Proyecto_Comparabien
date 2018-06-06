package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.DondeestudiarApplication;
import com.dondeestudiar.models.entities.Area;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.entities.Parametros;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DondeestudiarApplication.class)
public class CarreraServiceImplTest {

    @Autowired
    CarreraServiceImpl carreraService;

    @Test
    public void savingCarrera(){

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

        assertEquals(Carrera.class, carreraService.SaveAndVerify(obj));

    }

}