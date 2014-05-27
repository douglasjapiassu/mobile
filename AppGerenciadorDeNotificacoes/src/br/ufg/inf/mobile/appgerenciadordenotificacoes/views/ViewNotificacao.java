package br.ufg.inf.mobile.appgerenciadordenotificacoes.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity responsável por mostrar a mensagem na tela.
 */
public class ViewNotificacao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Definimos uma TextView para mostrar a mensagem na tela
		TextView texto = new TextView(getApplicationContext());
		// Define como texto da TextView a mensagem recebida do GCM
		Intent intent = getIntent();
		texto.setText(getIntent().getStringExtra("mensagem_recebida"));
		// Ajusta tamanho e cor da fonte
		texto.setTextSize(20.0F);
		texto.setTextColor(Color.BLACK);
		/*
		 * Para tornar as coisas mais simples, mostraremos apenas uma TextView
		 * na tela com o conteúdo da mensagem recebida da Nuvem através do GCM.
		 */
		setContentView(texto);
	}

}
