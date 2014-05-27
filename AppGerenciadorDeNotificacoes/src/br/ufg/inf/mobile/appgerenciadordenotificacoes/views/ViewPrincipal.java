package br.ufg.inf.mobile.appgerenciadordenotificacoes.views;


import java.util.Date;
import java.util.List;
import java.util.Random;

import br.ufg.inf.mobile.appgerenciadordenotificacoes.R;
import br.ufg.inf.mobile.appgerenciadordenotificacoes.model.Notificacao;
import br.ufg.inf.mobile.appgerenciadordenotificacoes.persistencia.DBAdapter;
import br.ufg.inf.mobile.appgerenciadordenotificacoes.service.AGNServiceConnection;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewPrincipal extends Activity {
    
	Button btnSalvar, btnCancelar, btnNovoCadastro, btnRegistrar, btnHistorico, btnVoltar;
	EditText txtNome, txtEndereco, txtTelefone;
	TextView tvNotificacoes;
	DBAdapter dbAdapter;
	private Intent intent;
	private AGNServiceConnection connection;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = new DBAdapter(this);
        
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
		//PendingIntent.FLAG_UPDATE_CURRENT
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

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
    	
    	btnHistorico = (Button) findViewById(R.id.btnHistorico);
    	btnHistorico.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//carregarNotificacoesSalvas(context);
				Intent intent = new Intent(context, ViewHistoricoNotificacoes.class);
				startActivity(intent);
			}});
        
    	
        btnNovoCadastro = (Button) findViewById(R.id.btnNovo);
        btnNovoCadastro.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//loadViewCadastro();
				//connection.getAGNService().mostraNotificacao("Teste", "as aa", context);
				mostraNotificacao("Teste", "Msg", v);
			}});
    }
    
    public void loadViewCadastro() {
    	setContentView(R.layout.cadastro);
    	
    	//configurando o botao cancelar
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				loadViewPrincipal();
			}});
        
        //configurando o formuario de cadastro
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEndereco = (EditText)findViewById(R.id.txtEndereco);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        
        //configurando o botao salvar
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				salvarNotificacao();
			}});
    }
    
    public void salvarNotificacao() {
    	DBAdapter dbAdapter = new DBAdapter(this);
    	
    	dbAdapter.salvarNotificacao(txtTelefone.getText().toString(), new Date());
		loadViewPrincipal();
    }
    
    private void iniciaService() {
		intent = new Intent("br.ufg.inf.mobile.appgerenciadordenotificacoes.action.ENVIAR_NOTIFICACAO");
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