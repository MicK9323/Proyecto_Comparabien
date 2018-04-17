package dao;

import interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new UsuarioDaoImpl();
	}

}
