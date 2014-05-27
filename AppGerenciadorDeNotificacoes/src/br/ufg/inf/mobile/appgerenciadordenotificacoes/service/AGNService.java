package br.ufg.inf.mobile.appgerenciadordenotificacoes.service;

import java.util.Random;

import br.ufg.inf.mobile.appgerenciadordenotificacoes.R;
import br.ufg.inf.mobile.appgerenciadordenotificacoes.views.ViewNotificacao;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

@SuppressWarnings("deprecation")
public class AGNService extends Service {
	
	private IBinder binder = new NotificacaoBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	public void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		// Tempo em que a Notificação será disparada
		long tempoDefinido = System.currentTimeMillis();
		Random rdn = new Random();
		int id = rdn.nextInt();
		
		mensagem += " " + id;

		// Objeto Notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, tempoDefinido);

		// Intent que será disparada quando o usuário clicar sobre a Notificação
		Intent intent = new Intent(context, ViewNotificacao.class);
		intent.putExtra("mensagem_recebida", mensagem);
		//PendingIntent.FLAG_UPDATE_CURRENT
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

		notification.setLatestEventInfo(context, titulo, mensagem, pendingIntent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_ALL;
		notification.vibrate = new long[] { 100, 250, 100, 500 };

		// Agenda a Notificação
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		notificationManager.notify(id , notification);
	}

}
