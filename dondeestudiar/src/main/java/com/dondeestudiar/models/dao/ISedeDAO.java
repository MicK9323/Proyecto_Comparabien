package com.dondeestudiar.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Sede;

@Repository("sedeDAO")
public interface ISedeDAO extends JpaRepository<Sede, Integer> {
	
	

}
