package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.DondeestudiarApplication;
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
    public void listarCarreras() {
    }

    @Test
    public void guardar() {
    }

    @Test
    public void buscar() {
    }
}