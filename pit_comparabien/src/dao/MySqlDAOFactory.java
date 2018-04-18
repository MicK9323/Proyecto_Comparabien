package dao;

import interfaces.EmpresaDAO;
import interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new UsuarioDaoImpl();
	}

	@Override
	public EmpresaDAO getEmpresaDAO() {
		// TODO Auto-generated method stub
		return new EmpresaDaoImpl();
	}

}
