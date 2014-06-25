package br.ufg.inf.es.mobile.views;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.ufg.inf.es.mobile.R;
import br.ufg.inf.es.mobile.util.AGNCommon;

public class UsuarioActivity extends Activity {
    
	Button btnAtivarDisciplinas, btnNotificacoes, btnSair;
	TextView tvBemVindo;
	private AGNCommon common;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        common = new AGNCommon(getApplicationContext());
        loadViewUsuario();
    }
    
    
    public void loadViewUsuario() {
    	setContentView(R.layout.activity_usuario);
        final Context context = this;
        tvBemVindo = (TextView) findViewById(R.id.tvBemVindo);
        
        if (common.isUsuarioLogado()) {
        	tvBemVindo.setText("Bem vindo, " + common.getUsuarioLogado().getNome());
        }
        
    	btnAtivarDisciplinas = (Button) findViewById(R.id.btnAtivarDisciplinas);
    	btnAtivarDisciplinas.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent = new Intent(context, ListaDisciplinasActivity.class);
				startActivity(intent);
			}});
        
        btnNotificacoes = (Button) findViewById(R.id.btnNotificacoes);
        btnNotificacoes.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent intent = new Intent(context, ListaNotificacoesActivity.class);
        		
				startActivity(intent);
        	}});
        
        btnSair = (Button) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		common.removerUsuarioDaSessao();
        		Intent intent = new Intent(context, LoginActivity.class);
        		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        		startActivity(intent);
        	}});
    }
    
    
}