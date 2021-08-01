package com.elektronskidnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="profesori")
public class Profesor extends Admin {


	@OneToMany(mappedBy = "profesor")
	private List<Predaje> predaju = new ArrayList<>();
}
