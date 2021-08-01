package com.elektronskidnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Ucenik;

@Repository
public interface UcenikRepository extends CrudRepository<Ucenik, Integer> {

	List<Ucenik> getAllByIdIn(List<Integer> ids);
}
