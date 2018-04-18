package beans;

public class UsuarioDTO {
	
	private String dni_user;
	private String nom_user;
	private String ape_user;
	private String usuario;
	private String clave;
	private String foto;
	private int id_rol;
	private boolean estado;
	private String fec_reg;
	private String nom_rol;
	
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
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getFec_reg() {
		return fec_reg;
	}
	public void setFec_reg(String fec_reg) {
		this.fec_reg = fec_reg;
	}
	public String getNom_rol() {
		return nom_rol;
	}
	public void setNom_rol(String nom_rol) {
		this.nom_rol = nom_rol;
	}
	
}
