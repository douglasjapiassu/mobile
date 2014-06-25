package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;

import br.ufg.inf.es.mobile.model.Notificacao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class NotificacaoDAO extends BaseDaoImpl<Notificacao, Integer> {

	public NotificacaoDAO(ConnectionSource connectionSource) throws SQLException {
		super(Notificacao.class);
		setConnectionSource(connectionSource);
		initialize();
	}
}