package beans;

import java.io.Serializable;

public class DepartamentoDTO implements Serializable{
	
	private static final long serialVersionUID = -435230020957773653L;
	
	private String id;
	private String nombre;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
