package com.dondeestudiar.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Usuario;

// TODO: Auto-generated Javadoc
/**
 * @author Miguel
 * Interfaz que hereda de JPARepository para manipular los datos mapeandolos en ña clase
 */
@Repository("usuarioDAO")
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	
	/**
	 * Login.
	 * LLamada a procedimiento almacenado para el login.
	 * @param vUsuario => usuario
	 * @param vClave => clave
	 * @return Objeto de la clase Usuario
	 * @throws Controlar cualquier excepcion al tratar de ejecutar el metodo
	 */
	@Query(value = "{call sp_login(:vUsuario, :vClave)}", nativeQuery = true)
	public Usuario login( @Param("vUsuario") String usuario, 
			@Param("vClave") String clave);
	
	/**
	 * Find by usuario.
	 * Metodo de consulta usando la tecnologia de JPA, busca un objeto de tipo
	 * Usuario segun su campo "usuario". Estaba implementado para Spring Security
	 * @param usuario => parametro de consulta
	 * @return Objeto de tipo Usuario
	 */
	public Usuario findByUsuario(String usuario);
	
}
