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
import com.elektronskidnevnik.repositories.OcenaRepository;

@RestController
@RequestMapping("/api/v1/ucenici")
public class UcenikController {

	@Autowired
	private OcenaRepository ocenaRepository;

	@GetMapping("/{ucenikId}")
	public List<PregledOcenaDto> getStudentGrades(@PathVariable("ucenikId") Integer ucenikId, @RequestParam(value = "ocena", required = false) Integer novaOcena) {
		List<Ocena> ocene;
		if(novaOcena == null) {
			ocene = ocenaRepository.findAllByUcenikId(ucenikId);
		} else {
			ocene = ocenaRepository.findAllByUcenikIdAndOcena(ucenikId, novaOcena);
		}

		List<PregledOcenaDto> pregledOcenaDtos = new ArrayList<>();
		for (Ocena ocena : ocene) {
			PregledOcenaDto pregledOcenaDto = new PregledOcenaDto();
			pregledOcenaDto.setImeProfesora(ocena.getPolugodiste().getRazredUcenika().getPredaje().getProfesor().getIme());
			pregledOcenaDto.setNazivPredmeta(ocena.getPolugodiste().getRazredUcenika().getPredaje().getNazivPredmeta());
			pregledOcenaDto.setNazivOdeljenja(ocena.getPolugodiste().getRazredUcenika().getNaziv());
			pregledOcenaDto.setNazivPolugodista(ocena.getPolugodiste().getNaziv());
			pregledOcenaDto.setImeUcenika(ocena.getUcenik().getIme());
			pregledOcenaDto.setOcena(ocena.getOcena());
			pregledOcenaDtos.add(pregledOcenaDto);
		}
		return pregledOcenaDtos;
	}
}
