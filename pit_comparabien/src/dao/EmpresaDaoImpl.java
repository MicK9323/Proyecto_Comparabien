package dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import beans.DepartamentoDTO;
import beans.EmpresaDTO;
import interfaces.EmpresaDAO;

public class EmpresaDaoImpl implements EmpresaDAO {
	
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpresaDTO> listaEmpresas() {
		List<EmpresaDTO> lista = new ArrayList<EmpresaDTO>();
		SqlSession session = null;
		try {
			System.out.println("++++++++++++++++inicia procedimiento");
			session = conexion.openSession();
			lista = session.selectList("execSpListaEmpresas");
			System.out.println("lista"+lista);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lista;
	}



	@Override
	public Map<String, Object> regEmpresa(EmpresaDTO emp) {
		Map<String, Object> retorno = new HashMap<String, Object>();
		int estado = -1;
		SqlSession session = null;
		try {
			session = conexion.openSession();
			estado = session.insert("execSpRegEmpresa",emp);
			if(estado != -1) {
				retorno.put("ok", "Registro Exitoso");
			}				
		} catch (Exception e) {
			retorno.put("error", e.getCause().toString());
		}finally {
			session.close();
		}
		return retorno;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<DepartamentoDTO> listaDepartamentos() {
		List<DepartamentoDTO> lista = new ArrayList<DepartamentoDTO>();
		SqlSession session = null;
		try {
			session = conexion.openSession();
			lista = session.selectList("execSpListaDepartamentos");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lista;
	}

}
