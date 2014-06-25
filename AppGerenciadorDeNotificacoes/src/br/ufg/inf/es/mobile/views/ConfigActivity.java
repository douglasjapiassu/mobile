package br.ufg.inf.es.mobile.views;

import br.ufg.inf.es.mobile.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfigActivity extends Activity {

	Button btnAtivarDisciplinas, btnAtivarRemetentes;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadViewConfiguracoes();
    }
    
    
    public void loadViewConfiguracoes() {
    	setContentView(R.layout.activity_configuracoes);
        final Context context = this;
        
    	btnAtivarDisciplinas = (Button) findViewById(R.id.btnAtivarDisciplinas);
    	btnAtivarDisciplinas.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent = new Intent(context, ListaDisciplinasActivity.class);
				startActivity(intent);
			}});
        
        btnAtivarRemetentes = (Button) findViewById(R.id.btnAtivarRemetentes);
        btnAtivarRemetentes.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent intent = new Intent(context, ListaNotificacoesActivity.class);
        		
				startActivity(intent);
        	}});
    }
    
}
