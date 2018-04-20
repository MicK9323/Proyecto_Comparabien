package com.comparabien.app.models.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_empresas")
public class EmpresaDTO implements Serializable {
	
	private static final long serialVersionUID = 9018145992064934195L;
	
	@Id
	private String id_emp;
	
	@NotEmpty
	private String ruc_empresa;
	
	@NotEmpty
	private String nom_empresa;
	
	@NotEmpty
	private String telf_empresa;
	
	@NotEmpty
	private String dir_empresa;
	
	@NotEmpty
	private String email_empresa;
	
	@NotEmpty
	private String cobertura_dep;
	
	@NotEmpty
	private String logo;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String fec_reg;
	
	private String[] rubros;
	
	public String getId_emp() {
		return id_emp;
	}
	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}
	public String getRuc_empresa() {
		return ruc_empresa;
	}
	public void setRuc_empresa(String ruc_empresa) {
		this.ruc_empresa = ruc_empresa;
	}
	public String getNom_empresa() {
		return nom_empresa;
	}
	public void setNom_empresa(String nom_empresa) {
		this.nom_empresa = nom_empresa;
	}
	public String getTelf_empresa() {
		return telf_empresa;
	}
	public void setTelf_empresa(String telf_empresa) {
		this.telf_empresa = telf_empresa;
	}
	public String getDir_empresa() {
		return dir_empresa;
	}
	public void setDir_empresa(String dir_empresa) {
		this.dir_empresa = dir_empresa;
	}
	public String getEmail_empresa() {
		return email_empresa;
	}
	public void setEmail_empresa(String email_empresa) {
		this.email_empresa = email_empresa;
	}
	public String getCobertura_dep() {
		return cobertura_dep;
	}
	public void setCobertura_dep(String cobertura_dep) {
		this.cobertura_dep = cobertura_dep;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getFec_reg() {
		return fec_reg;
	}
	public void setFec_reg(String fec_reg) {
		this.fec_reg = fec_reg;
	}
	public String[] getRubros() {
		return rubros;
	}
	public void setRubros(String[] rubros) {
		this.rubros = rubros;
	}

}
