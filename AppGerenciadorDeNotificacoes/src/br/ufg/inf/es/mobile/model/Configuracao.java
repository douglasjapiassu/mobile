package br.ufg.inf.es.mobile.model;

import java.util.Collection;

import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "configuracao")
public class Configuracao extends GenericModel {

	@ForeignCollectionField(eager = true)
	private Collection<Disciplina> disciplinas;
	
	public Configuracao() {
		
	}
	
	public Configuracao(String nome, String docente, Boolean isDisciplinaAtiva) {

	}
	
	
	@Override
	public String toString() {
		return 
    		"Disciplinas Ativas: " + this.disciplinas.toString();
	}


	public Collection<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Collection<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
