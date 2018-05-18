package com.dondeestudiar.models.dao;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Institucion;

@Repository("institucionesDAO")
public interface IInstitucionesDAO extends JpaRepository<Institucion, Integer> {

		public Institucion findByRuc(String ruc);
		
		public List<Institucion> findByNombreLikeIgnoreCase(String nombre);
		
		@Procedure
		public void sp_disabledInstitucion( @Param("id") int id );
		
		@Procedure
		public void sp_enabledInstitucion( @Param("id") int id );
	
}
