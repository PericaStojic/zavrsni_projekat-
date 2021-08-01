package com.elektronskidnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ucenici")
public class Ucenik extends Admin {

	
	@ManyToOne
	private Roditelj roditelj;

	public Roditelj getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj roditelj) {
		this.roditelj = roditelj;
	}

	
	
	
}