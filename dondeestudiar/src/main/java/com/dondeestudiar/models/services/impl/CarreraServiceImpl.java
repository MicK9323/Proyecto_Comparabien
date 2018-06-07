package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ICarreraDAO;
import com.dondeestudiar.models.entities.Carrera;
import com.dondeestudiar.models.services.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public boolean SaveAndVerify(Carrera obj) {
        if(carreraDAO.saveAndFlush(obj).equals(obj)){
            return true;
        }else{
            return false;
        }
    }
}
