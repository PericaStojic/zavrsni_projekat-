package com.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="predmeti")

public class Predaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String nazivPredmeta;

	@Column
	private Integer nedeljniFond;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Profesor profesor;

	@OneToMany(mappedBy = "predaje")
	private List<RazredUcenika> razrediUcenika = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public Integer getNedeljniFond() {
		return nedeljniFond;
	}

	public void setNedeljniFond(Integer nedeljniFond) {
		this.nedeljniFond = nedeljniFond;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<RazredUcenika> getRazrediUcenika() {
		return razrediUcenika;
	}

	public void setRazrediUcenika(List<RazredUcenika> razrediUcenika) {
		this.razrediUcenika = razrediUcenika;
	}

	
	
	
}
