package br.ufg.inf.es.mobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "disciplinas")
public class Disciplina extends GenericModel {

	@DatabaseField
	private String nome;
	
	@DatabaseField
	private String docente;
	
	@DatabaseField
	private Boolean isDisciplinaAtiva;
	
	@DatabaseField(columnName = "id_configuracao", foreign = true)
	private Configuracao configuracao;
	
	public Disciplina() {
		
	}
	
	public Disciplina(String nome, String docente, Boolean isDisciplinaAtiva) {
		this.nome = nome;
		this.docente = docente;
		this.isDisciplinaAtiva = isDisciplinaAtiva;
	}
	
	
	@Override
	public String toString() {
		return 
    		"Nome: " + this.getNome() +
    		"Docente: " + this.getDocente() +
    		"Disciplina Ativa: " + (isDisciplinaAtiva ? "Sim" : "Não");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}

	public Boolean getIsDisciplinaAtiva() {
		return isDisciplinaAtiva;
	}

	public void setIsDisciplinaAtiva(Boolean isDisciplinaAtiva) {
		this.isDisciplinaAtiva = isDisciplinaAtiva;
	}
}
