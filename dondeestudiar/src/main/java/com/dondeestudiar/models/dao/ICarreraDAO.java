package com.dondeestudiar.models.dao;

import com.dondeestudiar.models.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("carreraDAO")
public interface ICarreraDAO extends JpaRepository<Carrera, Integer> {



}
