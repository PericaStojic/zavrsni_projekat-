package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Polugodiste;

@Repository
public interface PolugodisteRepository extends CrudRepository<Polugodiste, Integer> {
}
