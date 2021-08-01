package com.elektronskidnevnik.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elektronskidnevnik.dto.KorisnikDto;
import com.elektronskidnevnik.entities.Admin;
import com.elektronskidnevnik.entities.Korisnik;
import com.elektronskidnevnik.entities.Profesor;
import com.elektronskidnevnik.entities.Roditelj;
import com.elektronskidnevnik.entities.Ucenik;
import com.elektronskidnevnik.enums.ROLE;
import com.elektronskidnevnik.repositories.AdminRepository;
import com.elektronskidnevnik.repositories.KorisnikRepository;
import com.elektronskidnevnik.repositories.ProfesorRepository;
import com.elektronskidnevnik.repositories.RoditeljRepository;
import com.elektronskidnevnik.repositories.UcenikRepository;

@RestController
@RequestMapping("/api/v1/korisnici")
public class KorisnikController {
	
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired
	private ProfesorRepository profesorRepository;

	@PostMapping
	public Korisnik kreirajKorisnika(@RequestBody KorisnikDto korisnikDto) {
		Korisnik u = new Korisnik();
		u.setPassword(korisnikDto.getPassword());
		u.setKorisnickoIme(korisnikDto.getKorisnickoIme());
		switch (korisnikDto.getRole()) {
			case ADMIN:
				u.setRole(ROLE.ADMIN);
				Admin admin = new Admin();
				admin.setIme(korisnikDto.getIme());
				admin.setPrezime(korisnikDto.getPrezime());
				adminRepository.save(admin);
				u.setAdmin(admin);
				saveUser(u);
				log.info("Dodat je novi admin: " + admin.getIme() + " " + admin.getPrezime());
				break;
			case RODITELJ:
				u.setRole(ROLE.RODITELJ);
				Roditelj roditelj = new Roditelj();
				roditelj.setIme(korisnikDto.getIme());
				roditelj.setPrezime(korisnikDto.getPrezime());
				roditelj.setEmail(korisnikDto.getEmail());
				adminRepository.save(roditelj);
				u.setAdmin(roditelj);
				saveUser(u);
				log.info("Dodat je novi roditelj: " + roditelj.getIme() + " " + roditelj.getPrezime());
				break;
			case UCENIK:
				u.setRole(ROLE.UCENIK);
				Ucenik ucenik = new Ucenik();
				ucenik.setIme(korisnikDto.getIme());
				ucenik.setPrezime(korisnikDto.getPrezime());
				adminRepository.save(ucenik);
				u.setAdmin(ucenik);
				saveUser(u);
				log.info("Dodat je novi ucenik: " + ucenik.getIme() + " " + ucenik.getPrezime());
				break;
			case PROFESOR:
				u.setRole(ROLE.PROFESOR);
				Profesor profesor = new Profesor();
				profesor.setIme(korisnikDto.getIme());
				profesor.setPrezime(korisnikDto.getPrezime());
				adminRepository.save(profesor);
				u.setAdmin(profesor);
				saveUser(u);
				log.info("Dodat je novi nastavnik: " + profesor.getIme() + " " + profesor.getPrezime());
				break;
		}
		return u;
	}

	@PostMapping("/dodajUcenika")
	public void dodajUcenikRoditelju(@RequestParam("roditeljId") Integer roditeljId, @RequestParam("ucenikId") Integer ucenikId) {
			Roditelj roditelj = roditeljRepository.findById(roditeljId).get();
			Ucenik ucenik = ucenikRepository.findById(ucenikId).get();
			ucenik.setRoditelj(roditelj);
			ucenikRepository.save(ucenik);
			log.info(String.format("Učenik %s %s je dete roditelja %s %s", ucenik.getIme(), ucenik.getPrezime(), roditelj.getIme(), roditelj.getPrezime()));
	}

	@PutMapping("/izmeniUcenika/{studentId}")
	public Ucenik izmeniUcenika(@PathVariable Integer studentId, @RequestBody Ucenik newStudent){
		Ucenik ucenik = ucenikRepository.findById(studentId).get();
		if(newStudent.getIme() != null) {
			ucenik.setIme(newStudent.getIme());
		}
		if(newStudent.getPrezime() != null) {
			ucenik.setPrezime(newStudent.getPrezime());
		}
		log.info(String.format("Učenik %s %s je izmenjen.", ucenik.getIme(), ucenik.getPrezime()));
		return ucenikRepository.save(ucenik);
	}

	@DeleteMapping("/izbrisiUcenika")
	public void izbrisiUcenika(@RequestParam("ucenikId") List<Integer> uceniciId) {
		log.info(String.format("Ucenik je izbrisan."));
		ucenikRepository.deleteAllById(uceniciId);
	}

	@PutMapping("/izmeniProfesora/{profesorId}")
	public Profesor izmeniProfesora(@PathVariable Integer profesorId, @RequestBody Profesor noviProfesor) {
		Profesor profesor = profesorRepository.findById(profesorId).get();
		if(noviProfesor.getIme() != null) {
			profesor.setIme(noviProfesor.getIme());
		}
		if(noviProfesor.getPrezime() != null) {
			profesor.setPrezime(noviProfesor.getPrezime());
		}
		log.info("Profesor je izmenjen.");
		return profesorRepository.save(profesor);
	}

	@DeleteMapping("/izbrisiProfesora")
	public void izbrisiProfesora(@RequestParam("profesorId") List<Integer> profesorId) {
		log.info("Profesor je izbrisan.");
		profesorRepository.deleteAllById(profesorId);
	}

	@PutMapping("/izmeniRoditelja/{roditeljId}")
	public Roditelj izmeniRoditelja(@PathVariable Integer roditeljId, @RequestBody Roditelj noviRoditelj) {
		Roditelj roditelj = roditeljRepository.findById(roditeljId).get();
		if(noviRoditelj.getIme() != null) {
			roditelj.setIme(noviRoditelj.getIme());
		}
		if(noviRoditelj.getPrezime() != null) {
			roditelj.setPrezime(noviRoditelj.getPrezime());
		}
		log.info("Roditelj je izmenjen.");
		return roditeljRepository.save(roditelj);
	}

	@DeleteMapping("/izbrisiRoditelja")
	public void izbrisiRoditelja(@RequestParam("roditeljId") List<Integer> roditeljId) {
		log.info("Roditelj je izbrisan.");
		roditeljRepository.deleteAllById(roditeljId);
	}

	private void saveUser(Korisnik korisnik) {
		korisnikRepository.save(korisnik);
	}
}
