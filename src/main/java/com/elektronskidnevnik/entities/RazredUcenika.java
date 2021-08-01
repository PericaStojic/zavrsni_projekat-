package com.elektronskidnevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "razred_ucenika")
@Entity
public class RazredUcenika {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String naziv;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Predaje predaje;

	@OneToMany(mappedBy = "razredUcenika")
	private List<Polugodiste> polugodista = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Predaje getPredaje() {
		return predaje;
	}

	public void setPredaje(Predaje predaje) {
		this.predaje = predaje;
	}

	public List<Polugodiste> getPolugodista() {
		return polugodista;
	}

	public void setPolugodista(List<Polugodiste> polugodista) {
		this.polugodista = polugodista;
	}
	
	
}