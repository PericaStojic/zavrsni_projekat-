package com.elektronskidnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elektronskidnevnik.entities.Ocena;
import com.elektronskidnevnik.entities.Ucenik;

@Repository
public interface OcenaRepository extends CrudRepository<Ocena, Integer> {

	List<Ocena> findAllByUcenikId(Integer ucenikId);

	List<Ocena> findAllByUcenikIdAndOcena(Integer ucenikId, Integer ocena);

	List<Ocena> findAllByUcenikIn(List<Ucenik> ucenici);

	List<Ocena> findAllByUcenikInAndOcena(List<Ucenik> ucenici, Integer ocena);
}
