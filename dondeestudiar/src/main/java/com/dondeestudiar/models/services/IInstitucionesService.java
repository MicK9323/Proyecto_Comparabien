package com.dondeestudiar.models.services;

import java.util.ArrayList;

import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;

public interface IInstitucionesService {
	
	public Institucion addInstitucion(Institucion obj);
	
	public Institucion findByRuc(String ruc);
	
	public void addSedes(ArrayList<Sede> sedes);
	
}
