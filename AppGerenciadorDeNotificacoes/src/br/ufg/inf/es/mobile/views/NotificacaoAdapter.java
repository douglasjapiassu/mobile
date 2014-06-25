package br.ufg.inf.es.mobile.views;

import java.util.List;

import br.ufg.inf.es.mobile.R;
import br.ufg.inf.es.mobile.model.Notificacao;
import br.ufg.inf.es.mobile.util.NotificacaoEnum;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificacaoAdapter extends BaseAdapter {
	
	TextView tvTipoNotificacao, tvRemetente, tvDataRecebimento, tvCabecalho;
	ImageView imgMsgLida;
	private LayoutInflater layoutInflater;
    private List<Notificacao> listaNotificacao;
    
    public NotificacaoAdapter(Context context, List<Notificacao> listaNotificacao) {
        this.listaNotificacao = listaNotificacao;
        layoutInflater = LayoutInflater.from(context);
    }

	@Override
	public int getCount() {
		 return listaNotificacao.size();
	}

	@Override
	public Notificacao getItem(int position) {
		return listaNotificacao.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Notificacao notificacao = listaNotificacao.get(position);
		view = layoutInflater.inflate(R.layout.item_notificacao, null);
		tvTipoNotificacao = (TextView) view.findViewById(R.id.tvTipoNotificacao);
		tvRemetente = (TextView) view.findViewById(R.id.tvRemetente);
		tvDataRecebimento = (TextView) view.findViewById(R.id.tvDataRecebimento);
		tvCabecalho = (TextView) view.findViewById(R.id.tvCabecalho);
		imgMsgLida = (ImageView) view.findViewById(R.id.icMsgLida);
		
		popularView(notificacao);
		
		return view;
	}
	
	public void popularView(Notificacao notificacao) {
		tvTipoNotificacao.setText(NotificacaoEnum.getTipoNotificacao(notificacao.getTipoNotificacao()));
		tvRemetente.setText(NotificacaoEnum.getTipoRemetente(notificacao.getTipoRemetente()));
		tvCabecalho.setText(notificacao.getCabecalho());
		tvDataRecebimento.setText(notificacao.getDataRecebimentoFormatada());
		
		int typeFace = Typeface.BOLD_ITALIC;
		
		if (notificacao.getIsMensagemLida()) {
			imgMsgLida.setImageResource(R.drawable.ic_check);
			typeFace = Typeface.NORMAL;
		}
		
		tvTipoNotificacao.setTypeface(null, typeFace);
		tvTipoNotificacao.setTextColor(Color.BLACK);
		tvRemetente.setTypeface(null, typeFace);
		tvCabecalho.setTypeface(null, typeFace);
		tvDataRecebimento.setTypeface(null, typeFace);
	}

}
