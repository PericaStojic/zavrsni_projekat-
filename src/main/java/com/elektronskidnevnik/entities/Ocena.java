package com.elektronskidnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ocene")
public class Ocena {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Min(1)
	@Max(5)
	private Integer ocena;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Polugodiste polugodiste;

	@ManyToOne
	private Ucenik ucenik;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public Polugodiste getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(Polugodiste polugodiste) {
		this.polugodiste = polugodiste;
	}

	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	

}
