package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;

import br.ufg.inf.es.mobile.model.Usuario;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class UsuarioDAO extends BaseDaoImpl<Usuario, Integer> {

	public UsuarioDAO(ConnectionSource connectionSource) throws SQLException {
		super(Usuario.class);
		setConnectionSource(connectionSource);
		initialize();
	}
}