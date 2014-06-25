package br.ufg.inf.es.mobile.service;

import android.os.Binder;

public class NotificacaoBinder extends Binder {

	private AGNService agnService;

	public NotificacaoBinder(AGNService agnService) {
		this.agnService = agnService;
	}

	/**
	 * Método responsável por permitir o acesso ao Service.
	 * 
	 * @return
	 */
	public AGNService getAGNService() {
		return agnService;
	}

}