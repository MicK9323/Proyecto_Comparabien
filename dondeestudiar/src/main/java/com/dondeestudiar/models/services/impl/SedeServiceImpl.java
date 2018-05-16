package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ISedeDAO;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeServiceImpl implements ISedeService {

    @Autowired
    ISedeDAO sedeDAO;

    @Override
    public Sede buscarSede(int id) {
        return sedeDAO.findById(id).orElse(null);
    }
}
