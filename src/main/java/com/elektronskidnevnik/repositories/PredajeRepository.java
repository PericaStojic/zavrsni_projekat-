package com.elektronskidnevnik.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Predaje;

@Repository
public interface PredajeRepository extends CrudRepository<Predaje, Integer> {

}
