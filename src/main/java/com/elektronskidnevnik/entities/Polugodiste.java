package com.elektronskidnevnik.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "polugodista")
@Entity
public class Polugodiste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String naziv;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private RazredUcenika razredUcenika;

	@OneToMany(mappedBy = "polugodiste")
	private List<Ocena> ocene = new ArrayList<>();

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

	public RazredUcenika getRazredUcenika() {
		return razredUcenika;
	}

	public void setRazredUcenika(RazredUcenika razredUcenika) {
		this.razredUcenika = razredUcenika;
	}

	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}
	
	
}