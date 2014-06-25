package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.ufg.inf.es.mobile.model.Configuracao;
import br.ufg.inf.es.mobile.model.Disciplina;
import br.ufg.inf.es.mobile.model.Notificacao;
import br.ufg.inf.es.mobile.model.Usuario;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "agn.db";
	private static final int DATABASE_VERSION = 2;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.i("INFO", "onCreate");
		try {
			TableUtils.createTableIfNotExists(connectionSource, Disciplina.class);
			TableUtils.createTableIfNotExists(connectionSource, Configuracao.class);
			TableUtils.createTableIfNotExists(connectionSource, Notificacao.class);
			TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Disciplina.class, true);
			TableUtils.dropTable(connectionSource, Configuracao.class, true);
			TableUtils.dropTable(connectionSource, Notificacao.class, true);
			TableUtils.dropTable(connectionSource, Usuario.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Erro ao Dropar as databases", e);
			throw new RuntimeException(e);
		}
	}
}