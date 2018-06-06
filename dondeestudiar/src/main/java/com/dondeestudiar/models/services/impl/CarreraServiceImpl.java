package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ICarreraDAO;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.services.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements ICarreraService {

    @Autowired
    ICarreraDAO carreraDAO;

    @Override
    public List<Carrera> listarCarreras() {
        return carreraDAO.findAll();
    }

    @Override
    public void guardar(Carrera obj) {
        carreraDAO.save(obj);
    }

    @Override
    public Carrera buscar(int id) {
        return carreraDAO.findById(id).orElse(null);
    }

    @Override
    public Carrera SaveAndVerify(Carrera obj) {
        return carreraDAO.saveAndFlush(obj);
    }
}