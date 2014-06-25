package br.ufg.inf.es.mobile.views;


import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.ufg.inf.es.mobile.R;
import br.ufg.inf.es.mobile.persistencia.AGNBuilder;
import br.ufg.inf.es.mobile.service.AGNServiceConnection;

@SuppressWarnings("deprecation")
public class ViewPrincipal extends Activity {
    
	Button btnLogin, btnNotificacoesPublicas;
	private Intent intent;
	private AGNServiceConnection connection;
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.view_principal, menu);
    	
    	return super.onCreateOptionsMenu(menu);
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	
    	if (item.getItemId() == R.id.action_settings) {
    		intent = new Intent(getApplicationContext(), ConfigActivity.class);
    	} else {
    		intent = new Intent(getApplicationContext(), LoginActivity.class);
    		
    	}
    	startActivity(intent);
    	return super.onOptionsItemSelected(item);
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /** Mock **/
		AGNBuilder builder = new AGNBuilder(getApplicationContext());
        builder.construct();
        
        iniciaService();
        conectaAoService();
        loadViewPrincipal();
    }
    
    public void mostraNotificacao(String titulo, String mensagem,
			View view) {

		// Tempo em que a Notificação será disparada
		long tempoDefinido = System.currentTimeMillis();
		
		Random rdn = new Random();
		int id = rdn.nextInt();
		
		mensagem += " " + id;

		// Objeto Notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, tempoDefinido);

		// Intent que será disparada quando o usuário clicar sobre a Notificação
		Intent intent = new Intent(this, ViewNotificacao.class);
		intent.putExtra("mensagem_recebida", mensagem);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

		notification.setLatestEventInfo(this, titulo, mensagem, pendingIntent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_ALL;
		notification.vibrate = new long[] { 100, 250, 100, 500 };

		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(id, notification);
	}
    
    public void loadViewPrincipal() {
    	setContentView(R.layout.activity_view_principal);
        final Context context = this;
    	
    	btnNotificacoesPublicas = (Button) findViewById(R.id.btnNotificacoesPublicas);
    	btnNotificacoesPublicas.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent = new Intent(context, ListaNotificacoesPublicasActivity.class);
				startActivity(intent);
			}});
        
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);
        	}});
    }
    
    private void iniciaService() {
		intent = new Intent("br.ufg.inf.mobile.es.action.ENVIAR_NOTIFICACAO");
		startService(intent);
	}

	/**
	 * Método responsável por conectar ao Service
	 */
	private void conectaAoService() {
		connection = new AGNServiceConnection();
		bindService(intent, connection, 0);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Desconecta do Service quando a Activity é destruída
		unbindService(connection);
	}
}