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
public class SedeServiceImplTest {

    @Autowired
    SedeServiceImpl sedeService;

    @Test
    public void buscarSede() {
    }

    @Test
    public void saveSede() {
    }

    @Test
    public void disabledSede() {
    }

    @Test
    public void enabledSede() {
    }
}