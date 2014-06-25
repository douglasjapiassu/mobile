package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;

import br.ufg.inf.es.mobile.model.Configuracao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ConfiguracaoDAO extends BaseDaoImpl<Configuracao, Integer> {

	public ConfiguracaoDAO(ConnectionSource connectionSource) throws SQLException {
		super(Configuracao.class);
		setConnectionSource(connectionSource);
		initialize();
	}
}