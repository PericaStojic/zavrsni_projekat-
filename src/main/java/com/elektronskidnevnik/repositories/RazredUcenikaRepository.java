package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.RazredUcenika;

@Repository
public interface RazredUcenikaRepository extends CrudRepository<RazredUcenika, Integer> {
}
