package com.elektronskidnevnik.dto;

import javax.validation.constraints.NotNull;

public class PredajeDto {

	@NotNull
	private String nazivPredmeta;
	@NotNull
	private int nedeljniFond;
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public int getNedeljniFond() {
		return nedeljniFond;
	}
	public void setNedeljniFond(int nedeljniFond) {
		this.nedeljniFond = nedeljniFond;
	}
	
	
	
	
}
