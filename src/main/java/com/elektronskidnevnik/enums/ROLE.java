package com.elektronskidnevnik.enums;

public enum ROLE {
	ADMIN("Admin"),
	RODITELJ("Roditelj"),
	UCENIK("Ucenik"),
	PROFESOR("Profesor");

	public String value;

	ROLE(String value) {
		this.value = value;
	}
}
