package com.dondeestudiar.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dondeestudiar.models.dao.IParametrosDAO;
import com.dondeestudiar.models.entities.Parametros;
import com.dondeestudiar.models.services.IParametrosService;

@Service
public class ParametrosServiceImpl implements IParametrosService {
	
	@Autowired
	IParametrosDAO parametrosDAO;
	
	@Override
	public List<Parametros> findByIdGrupo(String grupo) {
		return parametrosDAO.findByIdGrupo(grupo);
	}

}
