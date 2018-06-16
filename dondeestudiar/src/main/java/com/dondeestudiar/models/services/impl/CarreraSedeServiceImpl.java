package com.dondeestudiar.models.services.impl;

import com.dondeestudiar.models.dao.ICarreraSedeDAO;
import com.dondeestudiar.models.entities.CarreraSede;
import com.dondeestudiar.models.services.ICarreraSedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraSedeServiceImpl implements ICarreraSedeService {

    @Autowired
    ICarreraSedeDAO carreraSedeDAO;

    @Override
    public List<CarreraSede> sp_carrerasSede(int id) {
        return carreraSedeDAO.sp_carrerasSede(id);
    }

}
