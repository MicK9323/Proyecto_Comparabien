package beans;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import jdk.nashorn.internal.parser.JSONParser;

public class EmpresaDTO implements Serializable{
	
	private static final long serialVersionUID = -1843097614494137201L;
	
	private String id_emp;
	private String ruc_empresa;
	private String nom_empresa;
	private String telf_empresa;
	private String dir_empresa;
	private String email_empresa;
	private String cobertura_dep;
	private String logo;
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
