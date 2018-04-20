package dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
			session = conexion.openSession();
			lista = session.selectList("execSpListaEmpresas");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lista;
	}



	@Override
	public String regEmpresa(EmpresaDTO emp) {
//		Map<String, Object> retorno = new HashMap<String, Object>();
		String retorno = "";
		int estado = -1;
		SqlSession session = null;
		try {
			session = conexion.openSession();
			estado = session.insert("execSpRegEmpresa",emp);
			if(estado != -1) {
				retorno = "ok";
			}				
		} catch (Exception e) {
			retorno = e.getCause().toString();
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



	@Override
	public EmpresaDTO buscarEmpresa(String codigo) {
		EmpresaDTO empresa = null;
		SqlSession session = null;
		try {
			session = conexion.openSession();
			empresa = (EmpresaDTO) session.selectOne("execSpBuscarEmpresa", codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return empresa;
	}



	@Override
	public String uptEmpresa(EmpresaDTO emp) {
		String retorno = "";
		int estado = -1;
		SqlSession session = null;
		try {
			session = conexion.openSession();
			estado = session.insert("execSpUptEmpresa",emp);
			if(estado != -1) {
				retorno = "ok";
			}				
		} catch (Exception e) {
			retorno = e.getCause().toString();
		}finally {
			session.close();
		}
		return retorno;
	}



}
