package com.dondeestudiar.models.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_rol;
	
	@NotEmpty
    private String nom_rol;
	
	@Temporal(TemporalType.DATE)
    private Date fec_reg;
	
	@NotNull
    private boolean estado;
    
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getNom_rol() {
		return nom_rol;
	}
	public void setNom_rol(String nom_rol) {
		this.nom_rol = nom_rol;
	}
	public Date getFec_reg() {
		return fec_reg;
	}
	public void setFec_reg(Date fec_reg) {
		this.fec_reg = fec_reg;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
    
    
}
