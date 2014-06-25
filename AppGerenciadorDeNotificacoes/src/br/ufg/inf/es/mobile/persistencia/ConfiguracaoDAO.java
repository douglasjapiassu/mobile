package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;

import br.ufg.inf.es.mobile.model.Disciplina;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ConfiguracaoDAO extends BaseDaoImpl<Disciplina, Integer> {

	public ConfiguracaoDAO(ConnectionSource connectionSource) throws SQLException {
		super(Disciplina.class);
		setConnectionSource(connectionSource);
		initialize();
	}
}