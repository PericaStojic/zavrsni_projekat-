package com.elektronskidnevnik.dto;

import javax.validation.constraints.NotNull;

import com.elektronskidnevnik.enums.ROLE;

public class KorisnikDto {

	@NotNull
	private String korisnickoIme;
	@NotNull
	private String password;

	@NotNull
	private String ime;
	@NotNull
	private String prezime;

	private String email;

	@NotNull
	private ROLE role;

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}
	
	

}
