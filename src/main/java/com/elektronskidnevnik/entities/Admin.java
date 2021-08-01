package com.elektronskidnevnik.entities;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Column
	private String ime;

	@Column
	private String prezime;
	
	
	public Admin() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	
	

}
