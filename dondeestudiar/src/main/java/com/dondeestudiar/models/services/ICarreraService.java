package com.dondeestudiar.models.services;

import com.dondeestudiar.models.entities.Carrera;

import java.util.List;

public interface ICarreraService {

    public List<Carrera> listarCarreras();
    public void guardar(Carrera obj);
    public Carrera buscar(int id);

}
