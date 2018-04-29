package com.dondeestudiar.models.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dondeestudiar.models.dao.IInstitucionesDAO;
import com.dondeestudiar.models.dao.ISedeDAO;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.IInstitucionesService;

@Service
public class InstitucionService implements IInstitucionesService {
	
	@Autowired
	IInstitucionesDAO institucionesDAO;
	
	@Autowired
	ISedeDAO sedeDAO;

	@Override
	public Institucion addInstitucion(Institucion obj) {
		return institucionesDAO.saveAndFlush(obj);
	}

	@Override
	public Institucion findByRuc(String ruc) {
		return institucionesDAO.findByRuc(ruc);
	}

	@Override
	public void addSedes(ArrayList<Sede> sedes) {
		sedeDAO.saveAll(sedes);		
	}
		
	
	

}
