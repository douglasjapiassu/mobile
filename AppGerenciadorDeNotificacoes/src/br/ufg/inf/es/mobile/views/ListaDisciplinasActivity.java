package br.ufg.inf.es.mobile.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import br.ufg.inf.es.mobile.model.Disciplina;
import br.ufg.inf.es.mobile.persistencia.ConfiguracaoDAO;
import br.ufg.inf.es.mobile.persistencia.DatabaseHelper;
import br.ufg.inf.es.mobile.persistencia.DisciplinaDAO;
import br.ufg.inf.es.mobile.util.AGNCommon;

public class ListaDisciplinasActivity extends ListActivity  {
    
	DatabaseHelper helper;
	DisciplinaDAO disciplinaDAO;
	ConfiguracaoDAO configuracaoDAO;
	DisciplinaAdapter adapter;
	AGNCommon common;
	List<Disciplina> listaDisciplinas;
	String matricula;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        common = new AGNCommon(getApplicationContext());
        registerForContextMenu(getListView());
        
        helper = new DatabaseHelper(getApplicationContext());
        listaDisciplinas = new ArrayList<Disciplina>();
        matricula = common.getUsuarioLogado().getMatricula();
        
        setarAdapterOrdenadoPor("id", true);
    }
    
    public void setarAdapterOrdenadoPor(String colunaOrderBy, Boolean isCrescente) {
    	listaDisciplinas = new ArrayList<Disciplina>();
    	
    	try {
			disciplinaDAO = new DisciplinaDAO(helper.getConnectionSource());
			listaDisciplinas = disciplinaDAO.queryBuilder().orderBy(colunaOrderBy, isCrescente).query();
			
			adapter = new DisciplinaAdapter(getApplicationContext(), listaDisciplinas);
	        
	        setListAdapter(adapter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
    	
    	setDisciplinaAtiva(position);
    }
    private void setDisciplinaAtiva(int position) {
    	Disciplina disciplina = adapter.getItem(position);
    	Boolean isDisciplinaAtiva = !disciplina.getIsDisciplinaAtiva();
    	
    	disciplina.setIsDisciplinaAtiva(isDisciplinaAtiva);
    	
    	try {
    		disciplinaDAO.update(disciplina);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	adapter.notifyDataSetChanged();
    }
}