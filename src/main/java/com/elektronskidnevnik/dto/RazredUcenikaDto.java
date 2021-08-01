package com.elektronskidnevnik.dto;

import javax.validation.constraints.NotNull;

public class RazredUcenikaDto {

	@NotNull
	private String naziv;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

		
}
