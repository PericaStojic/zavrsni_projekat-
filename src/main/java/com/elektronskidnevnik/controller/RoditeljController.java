package com.elektronskidnevnik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elektronskidnevnik.dto.PregledOcenaDto;
import com.elektronskidnevnik.entities.Ocena;
import com.elektronskidnevnik.entities.Ucenik;
import com.elektronskidnevnik.repositories.OcenaRepository;
import com.elektronskidnevnik.repositories.RoditeljRepository;

@RestController
@RequestMapping("/api/v1/roditelji")
public class RoditeljController {

	
	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private OcenaRepository ocenaRepository;

	@GetMapping("/{roditeljId}")
	public List<PregledOcenaDto> getStudentGrades(@PathVariable("roditeljId") Integer roditeljId, @RequestParam(value = "ocena", required = false) Integer ocena) {
		List<Ucenik> ucenici = roditeljRepository.findById(roditeljId).get().getUcenici();
		List<Ocena> ocene;
		if(ocena == null) {
			ocene = ocenaRepository.findAllByUcenikIn(ucenici);
		} else {
			ocene = ocenaRepository.findAllByUcenikInAndOcena(ucenici, ocena);
		}

		List<PregledOcenaDto> pregledOcenaDtos = new ArrayList<>();
		for (Ocena o : ocene) {
			PregledOcenaDto pregledOcenaDto = new PregledOcenaDto();
			pregledOcenaDto.setImeProfesora(o.getPolugodiste().getRazredUcenika().getPredaje().getProfesor().getIme());
			pregledOcenaDto.setNazivPredmeta(o.getPolugodiste().getRazredUcenika().getPredaje().getNazivPredmeta());
			pregledOcenaDto.setNazivOdeljenja(o.getPolugodiste().getRazredUcenika().getNaziv());
			pregledOcenaDto.setNazivPolugodista(o.getPolugodiste().getNaziv());
			pregledOcenaDto.setImeUcenika(o.getUcenik().getIme());
			pregledOcenaDto.setOcena(o.getOcena());
			pregledOcenaDtos.add(pregledOcenaDto);
			
		}
		return pregledOcenaDtos;
	}
}
