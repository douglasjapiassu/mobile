package br.ufg.inf.mobile.appgerenciadordenotificacoes.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class AGNServiceConnection implements ServiceConnection {

	private AGNService agnService;

	@Override
	public void onServiceConnected(ComponentName component, IBinder binder) {
		NotificacaoBinder notificacaoBinder = (NotificacaoBinder) binder;
		this.agnService = notificacaoBinder.getAGNService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO
	}

	public AGNService getAGNService() {
		return agnService;
	}

}