package br.ufg.inf.es.mobile.util;

import java.sql.SQLException;

import br.ufg.inf.es.mobile.model.Usuario;
import br.ufg.inf.es.mobile.persistencia.DatabaseHelper;
import br.ufg.inf.es.mobile.persistencia.UsuarioDAO;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ContextMenu;
import android.view.Menu;

public class AGNCommon {
	
	public static final String SESSION = "SESSION";
	
	private Context context;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private DatabaseHelper helper;
	private UsuarioDAO usuarioDAO;
	
	public AGNCommon(Context context) {
		this.context = context;
		this.preferences = context.getSharedPreferences(SESSION, 0);
		this.editor = preferences.edit();
		this.helper = new DatabaseHelper(context);
	}
	
	public ContextMenu contextMenuNotificacoes(ContextMenu contextMenu) {
		contextMenu.add("Marcar como lida");
		contextMenu.add("Marcar como não lida");
		
		return contextMenu;
	}
	
	public Menu menuNotificacoes(Menu menu) {
		menu.add("Ordenar por Data Crescente");
		menu.add("Ordenar por Data Decrescente");
		menu.add("Ordenar por Remetente Crescente");
		menu.add("Ordenar por Remetente Decrescente");
		
		return menu;
	}
	
	public void adicionarUsuarioNaSessao(Usuario usuario) {
		preferences = context.getSharedPreferences(SESSION, 0);
		editor = preferences.edit();
		
		editor.remove("isLogado");
		editor.putBoolean("isLogado", true);
		editor.putInt("id", usuario.getId());
		
		editor.commit();
	}
	
	public Boolean isUsuarioLogado() {
		return preferences.getBoolean("isLogado", false);
	}
	
	public Usuario getUsuarioLogado() {
		Usuario usuario = new Usuario();
		int id = preferences.getInt("id", 0);
		
		try {
			usuarioDAO = new UsuarioDAO(this.helper.getConnectionSource());
			
			usuario = usuarioDAO.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public void removerUsuarioDaSessao() {
		editor.remove("isLogado");
		editor.putBoolean("isLogado", false);
		
		editor.commit();
	}

}
