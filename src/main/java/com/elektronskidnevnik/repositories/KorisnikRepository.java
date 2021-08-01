package com.elektronskidnevnik.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Korisnik;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik, Integer> {
}
