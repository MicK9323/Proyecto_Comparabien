package com.dondeestudiar.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.dondeestudiar.utils.Constantes;

@Entity
@Table(name = "tb_instituciones")
public class Institucion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_institucion")
	private int id;

	@Column(name = "ruc")
	@NotEmpty
	@Size(min = 11, max = 11)
	@Pattern(regexp = "[0-9]{11}")
	private String ruc;

	@Column(name = "nom_institucion")
	@NotEmpty
	@Size(min = 10, max = 100)
	@Pattern(regexp = "[A-Za-zÁÉÍÓÚñáéíóúÑ\\s?]{10,100}")
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_institucion")
	private Parametros tipoInstitucion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_gestion")
	private Parametros tipoGestion;

	@Column(name = "telf_institucion")
	@NotEmpty
	@Size(min = 7, max = 10)
	@Pattern(regexp = "[0-9]{7,10}")
	private String telf;

	@Column(name = "dir_web")
	@NotEmpty
	@Pattern(regexp = "(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\?=.-]*)*\\/?")
	private String website;

	@Column(name = "reputacion")
	private int popularidad;

	@Column(name = "residencial")
	private boolean residencial;

	@Column(name = "logo")
	private String logo;

	@Column(name = "estado")
	private boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fec_reg")
	private Date fecReg;

	@OneToMany(mappedBy = "institucion", fetch = FetchType.LAZY)
	List<Sede> sedes;

	@PrePersist
	private void prePersist() {
		this.popularidad = 0;
		this.estado = true;
		this.fecReg = new Date();
	}

	public int countSedes() {
		return sedes.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Parametros getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(Parametros tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}

	public Parametros getTipoGestion() {
		return tipoGestion;
	}

	public void setTipoGestion(Parametros tipoGestion) {
		this.tipoGestion = tipoGestion;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(int popularidad) {
		this.popularidad = popularidad;
	}

	public boolean isResidencial() {
		return residencial;
	}

	public void setResidencial(boolean residencial) {
		this.residencial = residencial;
	}

	public String getLogo() {
		if (this.logo.isEmpty() || this.logo == "")
			return Constantes.NOT_FOUND;
		else
			return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFecReg() {
		return fecReg;
	}

	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}
	
	public List<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes = sedes;
	}

}