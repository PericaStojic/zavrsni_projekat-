package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Roditelj;
import com.elektronskidnevnik.entities.Ucenik;

@Repository
public interface RoditeljRepository extends CrudRepository<Roditelj, Integer> {

	Roditelj findByUcenici(Ucenik ucenik);
}
