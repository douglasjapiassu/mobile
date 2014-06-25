package br.ufg.inf.es.mobile.views;

import java.util.List;

import br.ufg.inf.es.mobile.R;
import br.ufg.inf.es.mobile.model.Disciplina;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DisciplinaAdapter extends BaseAdapter {
	
	TextView tvDisciplina;
	ImageView imgAtivo;
	private LayoutInflater layoutInflater;
    private List<Disciplina> listaDisciplinas;
    
    public DisciplinaAdapter(Context context, List<Disciplina> listaDisciplinas) {
        this.listaDisciplinas = listaDisciplinas;
        layoutInflater = LayoutInflater.from(context);
    }
    
	@Override
	public int getCount() {
		 return listaDisciplinas.size();
	}

	@Override
	public Disciplina getItem(int position) {
		return listaDisciplinas.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Disciplina disciplina = listaDisciplinas.get(position);
		view = layoutInflater.inflate(R.layout.item_disciplina_configuracao, null);
		tvDisciplina = (TextView) view.findViewById(R.id.tvDisciplina);
		imgAtivo = (ImageView) view.findViewById(R.id.icAtivo);
		
		popularView(disciplina);
		
		return view;
	}
	
	public void popularView(Disciplina disciplina) {
		tvDisciplina.setText(disciplina.getNome());
		
		int typeFace = Typeface.NORMAL;
		
		imgAtivo.setImageResource(R.drawable.ic_not_check);
		
		if (disciplina.getIsDisciplinaAtiva()) {
			typeFace = Typeface.BOLD_ITALIC;
			tvDisciplina.setTextColor(Color.BLACK);
			imgAtivo.setImageResource(R.drawable.ic_check);
		}
		
		tvDisciplina.setTypeface(null, typeFace);
		tvDisciplina.setTextColor(Color.BLACK);
	}

}
