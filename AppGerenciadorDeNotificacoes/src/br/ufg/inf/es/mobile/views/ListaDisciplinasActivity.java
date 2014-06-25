package br.ufg.inf.es.mobile.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import br.ufg.inf.es.mobile.model.Configuracao;
import br.ufg.inf.es.mobile.model.Disciplina;
import br.ufg.inf.es.mobile.model.Usuario;
import br.ufg.inf.es.mobile.persistencia.ConfiguracaoDAO;
import br.ufg.inf.es.mobile.persistencia.DatabaseHelper;
import br.ufg.inf.es.mobile.persistencia.DisciplinaDAO;
import br.ufg.inf.es.mobile.util.AGNCommon;

public class ListaDisciplinasActivity extends ListActivity  {
    
	DatabaseHelper helper;
	DisciplinaDAO disciplinaDAO;
	ConfiguracaoDAO configuracaoDAO;
	DisciplinaAdapter adapter;
	Configuracao configuracao;
	Usuario usuario;
	AGNCommon common;
	List<Disciplina> listaDisciplinas;
	List<Disciplina> listaDisciplinasConfiguracao;
	String matricula;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        common = new AGNCommon(getApplicationContext());
        registerForContextMenu(getListView());
        
        helper = new DatabaseHelper(getApplicationContext());
        listaDisciplinas = new ArrayList<Disciplina>();
        usuario = common.getUsuarioLogado();
        matricula = usuario.getMatricula();
        
        setarAdapterOrdenadoPor("id", true);
    }
    
    public void setarAdapterOrdenadoPor(String colunaOrderBy, Boolean isCrescente) {
    	listaDisciplinas = new ArrayList<Disciplina>();
    	
    	try {
    		configuracaoDAO = new ConfiguracaoDAO(helper.getConnectionSource());
			disciplinaDAO = new DisciplinaDAO(helper.getConnectionSource());
			configuracao = configuracaoDAO.queryForId(usuario.getConfiguracao().getId());
			configuracaoDAO.refresh(configuracao);
			listaDisciplinasConfiguracao = new ArrayList<Disciplina>(configuracao.getDisciplinas());
			listaDisciplinas = disciplinaDAO.queryBuilder().orderBy(colunaOrderBy, isCrescente).query();
			
			adapter = new DisciplinaAdapter(getApplicationContext(), listaDisciplinas, listaDisciplinasConfiguracao);
	        
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
    	
    	if(disciplina.getConfiguracao() == null) {
    		disciplina.setConfiguracao(configuracao);
    	} else {
    		disciplina.setConfiguracao(new Configuracao());
    	}
    	
    	try {
    		disciplinaDAO.update(disciplina);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	setarAdapterOrdenadoPor("id", true);
    }
}