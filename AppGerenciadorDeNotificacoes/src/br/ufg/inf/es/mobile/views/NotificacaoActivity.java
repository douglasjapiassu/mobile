package br.ufg.inf.es.mobile.views;


import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import br.ufg.inf.es.mobile.R;
import br.ufg.inf.es.mobile.model.Notificacao;
import br.ufg.inf.es.mobile.persistencia.DatabaseHelper;
import br.ufg.inf.es.mobile.persistencia.NotificacaoDAO;
import br.ufg.inf.es.mobile.util.AGNCommon;
import br.ufg.inf.es.mobile.util.NotificacaoEnum;

public class NotificacaoActivity extends Activity {
	
	private Notificacao notificacao;
	private NotificacaoDAO notificacaoDAO;
	private DatabaseHelper helper;
	private TextView tvTipoNotificacao, tvCabecalho, tvMensagem, tvRemetente, tvDataRecebimento;
	AGNCommon common;
    
	/*@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		common = new AGNCommon(getApplicationContext()); 
		menu = common.optionsNotificacao(menu);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}*/
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadViewUsuario();
    }
    
    
    public void loadViewUsuario() {
    	setContentView(R.layout.activity_notificacao);
    	helper = new DatabaseHelper(getApplicationContext());
    	
    	tvTipoNotificacao = (TextView) findViewById(R.id.tvTipoNotificacao);
		tvCabecalho = (TextView) findViewById(R.id.tvCabecalho);
		tvMensagem = (TextView) findViewById(R.id.tvMensagem);
		tvRemetente = (TextView) findViewById(R.id.tvRemetente);
		tvDataRecebimento = (TextView) findViewById(R.id.tvDataRecebimento);
        
        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("id"));
        
        try {
			notificacaoDAO = new NotificacaoDAO(helper.getConnectionSource());
			notificacao = notificacaoDAO.queryForId(id);
			popularNotificacao(notificacao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	private void popularNotificacao(Notificacao notificacao) {
		tvTipoNotificacao.setText(NotificacaoEnum.getTipoNotificacao(notificacao.getTipoNotificacao()));
		tvCabecalho.setText(notificacao.getCabecalho());
		tvMensagem.setText(notificacao.getMensagem());
		tvRemetente.setText(NotificacaoEnum.getTipoRemetente(notificacao.getTipoRemetente()));
		tvDataRecebimento.setText(notificacao.getDataRecebimentoFormatada());
	}
    
    
}