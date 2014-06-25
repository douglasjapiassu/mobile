package br.ufg.inf.es.mobile.model;

import com.j256.ormlite.field.DatabaseField;

public abstract class GenericModel {

	@DatabaseField(generatedId = true)
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
