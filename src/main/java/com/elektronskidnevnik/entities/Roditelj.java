package com.elektronskidnevnik.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Table(name="roditelji")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Roditelj extends Admin {

	@Column
	private String email;

	@JsonIgnore
	@OneToMany
	private List<Ucenik> ucenici;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ucenik> getUcenici() {
		return ucenici;
	}

	public void setUcenici(List<Ucenik> ucenici) {
		this.ucenici = ucenici;
	}

	
	
	
}
