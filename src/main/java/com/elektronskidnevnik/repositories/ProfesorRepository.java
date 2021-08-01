package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
	
	
}
