package br.ufg.inf.es.mobile.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.Where;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import br.ufg.inf.es.mobile.model.Notificacao;
import br.ufg.inf.es.mobile.persistencia.DatabaseHelper;
import br.ufg.inf.es.mobile.persistencia.NotificacaoDAO;
import br.ufg.inf.es.mobile.util.AGNCommon;

@SuppressWarnings("unchecked")
public class ListaNotificacoesActivity extends ListActivity  {
    
	DatabaseHelper helper;
	NotificacaoDAO notificacaoDAO;
	NotificacaoAdapter adapter;
	OnLongClickListener onLongClickListener;
	AGNCommon common;
	List<Notificacao> listaNotificacoes;
	String matricula;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        common = new AGNCommon(getApplicationContext());
        registerForContextMenu(getListView());
        
        helper = new DatabaseHelper(getApplicationContext());
        listaNotificacoes = new ArrayList<Notificacao>();
        matricula = common.getUsuarioLogado().getMatricula();
        
        setarAdapterOrdenadoPor("id", true);
    }
    
	public void setarAdapterOrdenadoPor(String colunaOrderBy, Boolean isCrescente) {
    	listaNotificacoes = new ArrayList<Notificacao>();
    	
    	try {
			notificacaoDAO = new NotificacaoDAO(helper.getConnectionSource());
			//Where<Notificacao, Integer> where = notificacaoDAO.queryBuilder().where();
			//listaNotificacoes = notificacaoDAO.queryBuilder().orderBy(colunaOrderBy, isCrescente).where().and(where.eq("matricula", matricula), where.eq("matricula", matricula)).query();
			listaNotificacoes = notificacaoDAO.queryBuilder().orderBy(colunaOrderBy, isCrescente).where().eq("matricula", matricula).query();
			
			adapter = new NotificacaoAdapter(getApplicationContext(), listaNotificacoes);
	        
	        setListAdapter(adapter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	setIsMensagemLida(position, true);
    	
    	visualizarMensagem(String.valueOf(id));
    }
    
    private void visualizarMensagem(String id) {
    	Intent intent = new Intent(getApplicationContext(), NotificacaoActivity.class);
    	intent.putExtra("id", id);
    	startActivity(intent);
	}

	@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	menu = common.contextMenuNotificacoes(menu);
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		 
	     int position = adapterContextMenuInfo.position;
		 boolean clickMsgLida = true;
	     
		 if (item.getTitle().toString().contains("não"))
			 clickMsgLida = false;
	      
		 //quando o item clicado for o msgLida, setará true, caso contrário false
		 setIsMensagemLida(position, clickMsgLida);
	      
	     return super.onOptionsItemSelected(item);  
	}
	
	private void setIsMensagemLida(int position, Boolean isMensagemLida) {
		Notificacao notificacao = adapter.getItem(position);
		notificacao.setIsMensagemLida(isMensagemLida);
    	
    	try {
			notificacaoDAO.update(notificacao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	adapter.notifyDataSetChanged();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu = common.menuNotificacoes(menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String tipo = item.getTitle().toString();
		String colunaOrderBy = "id";
		Boolean isCrescente = tipo.contains("Decrescente") == false;
		
		if (tipo.contains("Data")) {
			colunaOrderBy = "data_recebimento";
		} else {
			colunaOrderBy = "tipo_remetente";
		}
		
		setarAdapterOrdenadoPor(colunaOrderBy, isCrescente);
		
		return super.onOptionsItemSelected(item);
	}

}