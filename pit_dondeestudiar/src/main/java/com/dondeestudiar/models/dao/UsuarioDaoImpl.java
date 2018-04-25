package com.dondeestudiar.models.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.dondeestudiar.models.entities.Usuario;
import com.dondeestudiar.models.interfaces.IUsuarioDAOCustom;

@Repository
public class UsuarioDaoImpl implements IUsuarioDAOCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Map<String, Object> login(String usuario, String clave) {
		Map<String, Object> retorno = new LinkedHashMap<String, Object>();
		Usuario user = null;
		Boolean call;
		try {
			StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("spLogin");
			sp.setParameter("vUsuario", usuario);
			sp.setParameter("vClave", clave);
			call = sp.execute();
			if( call ) {
				user = (Usuario) sp.getSingleResult();
				retorno.put("usuario", user);
			}			
		}catch (Exception e) {
			retorno.put("error", e.getCause().toString());
		} finally {
			em.close();
		}		
		return retorno;
	}

}
