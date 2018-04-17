package dao;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import beans.UsuarioDTO;
import interfaces.UsuarioDAO;

public class UsuarioDaoImpl implements UsuarioDAO {
	
	SqlSessionFactory conexion = null;
	
	{
		try {
			String file = "ConfiguracionIbatis.xml";
			Reader reader = Resources.getResourceAsReader(file);
			conexion = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, Object> loginUsuario(String usuario, String clave) {
		UsuarioDTO user = null;
		Map<String, Object> retorno = new HashMap<String, Object>();
		SqlSession session = conexion.openSession();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("vUsuario", usuario);
			param.put("vClave", clave);
			user = (UsuarioDTO) session.selectOne("execSpLogin",param);
			retorno.put("logedUser", user);
		} catch (Exception e) {
			retorno.put("error", e.getCause().toString());
		}finally {
			session.close();
		}
		return retorno;
	}

}
