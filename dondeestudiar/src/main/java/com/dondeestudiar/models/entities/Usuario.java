package com.dondeestudiar.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String dni_user;

	@NotEmpty
	private String nom_user;

	@NotEmpty
	private String ape_user;

	@NotEmpty
	private String usuario;

	@NotEmpty
	private String clave;

	@Column(name = "ruta_foto")
	private String rutaFoto;

	private String foto;

	@NotNull
	private boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fec_reg;

	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;
	
	public String getNombres() {
		return String.format("%s %s", this.nom_user, this.ape_user);
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getDni_user() {
		return dni_user;
	}

	public void setDni_user(String dni_user) {
		this.dni_user = dni_user;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getApe_user() {
		return ape_user;
	}

	public void setApe_user(String ape_user) {
		this.ape_user = ape_user;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFec_reg() {
		return fec_reg;
	}

	public void setFec_reg(Date fec_reg) {
		this.fec_reg = fec_reg;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
}
