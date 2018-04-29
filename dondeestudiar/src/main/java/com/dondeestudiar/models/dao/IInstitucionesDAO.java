package com.dondeestudiar.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Institucion;

@Repository("institucionesDAO")
public interface IInstitucionesDAO extends JpaRepository<Institucion, Integer> {

		public Institucion findByRuc(String ruc);
	
}
