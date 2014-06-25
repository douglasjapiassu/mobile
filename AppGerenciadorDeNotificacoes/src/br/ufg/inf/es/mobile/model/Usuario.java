package br.ufg.inf.es.mobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuario")
public class Usuario extends GenericModel {

	@DatabaseField
	private String matricula;
	
	@DatabaseField
	private String senha;
	
	@DatabaseField
	private String nome;
	
	public Usuario() {
		
	}
	
	public Usuario(String matricula, String senha, String nome) {
		super();
		this.matricula = matricula;
		this.senha = senha;
		this.nome = nome;
	}



	@Override
	public String toString() {
		return 
			"Matrícula: "+ this.matricula + "\n" + 
    		"Nome: "+ this.getNome();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
