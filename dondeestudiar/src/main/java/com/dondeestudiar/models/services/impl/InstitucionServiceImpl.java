package com.dondeestudiar.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dondeestudiar.models.dao.IInstitucionesDAO;
import com.dondeestudiar.models.dao.ISedeDAO;
import com.dondeestudiar.models.entities.Institucion;
import com.dondeestudiar.models.entities.Sede;
import com.dondeestudiar.models.services.IInstitucionesService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstitucionServiceImpl implements IInstitucionesService {
	
	@Autowired
	IInstitucionesDAO institucionesDAO;
	
	@Autowired
	ISedeDAO sedeDAO;

	@Override
	@Transactional
	public Institucion addInstitucion(Institucion obj) {
		return institucionesDAO.saveAndFlush(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public Institucion findByRuc(String ruc) {
		return institucionesDAO.findByRuc(ruc);
	}

	@Override
	@Transactional
	public void addSedes(ArrayList<Sede> sedes) {
		sedeDAO.saveAll(sedes);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Institucion> listarInstituciones() {
		return institucionesDAO.findAll();
	}

	@Override
	@Transactional
	public void saveInstitucion(Institucion obj) {
		institucionesDAO.save(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Institucion> findByNombre(String nombre) {
		return institucionesDAO.findByNombreLikeIgnoreCase(nombre);
	}

	@Override
	@Transactional
	public void disabledInstitucion(int id) {
		institucionesDAO.sp_disabledInstitucion(id);
	}

	@Override
	@Transactional
	public void enabledInstitucion(int id) {
		institucionesDAO.sp_enabledInstitucion(id);
	}
		
	
	

}
