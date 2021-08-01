package com.elektronskidnevnik.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elektronskidnevnik.dto.PolugodisteDto;
import com.elektronskidnevnik.dto.RazredUcenikaDto;
import com.elektronskidnevnik.dto.PredajeDto;
import com.elektronskidnevnik.entities.Ocena;
import com.elektronskidnevnik.entities.Polugodiste;
import com.elektronskidnevnik.entities.Ucenik;
import com.elektronskidnevnik.entities.RazredUcenika;
import com.elektronskidnevnik.entities.Profesor;
import com.elektronskidnevnik.entities.Predaje;
import com.elektronskidnevnik.models.EmailObject;
import com.elektronskidnevnik.repositories.OcenaRepository;
import com.elektronskidnevnik.repositories.PolugodisteRepository;
import com.elektronskidnevnik.repositories.RazredUcenikaRepository;
import com.elektronskidnevnik.repositories.UcenikRepository;
import com.elektronskidnevnik.repositories.ProfesorRepository;
import com.elektronskidnevnik.repositories.PredajeRepository;
import com.elektronskidnevnik.services.EmailService;

@RestController
@RequestMapping("/api/v1/skola")
public class SkolaController {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private PredajeRepository predajeRepository;

	@Autowired
	private PolugodisteRepository polugodisteRepository;

	@Autowired
	private RazredUcenikaRepository razredUcenikaRepository;

	@Autowired
	private UcenikRepository ucenikRepository;

	@Autowired OcenaRepository ocenaRepository;
	
	@Autowired
	private EmailService emailService;


	@GetMapping
	public Profesor pokaziProfesora(@RequestParam("profesorId") Integer profesorId) {
		return profesorRepository.findById(profesorId).get();
	}

	@PostMapping("/profesor/{profesorId}/predaje")
	public Predaje dodajPredmetProfesoru(@PathVariable("profesorId") Integer profesorId, @RequestBody PredajeDto predajeDto) {
		Profesor profesor = profesorRepository.findById(profesorId).get();
		Predaje ts = new Predaje();
		ts.setNazivPredmeta(predajeDto.getNazivPredmeta());
		ts.setNedeljniFond(predajeDto.getNedeljniFond());
		ts.setProfesor(profesor);
		log.info("Dodat je predmet nastavniku: " + ts.toString());
		return predajeRepository.save(ts);
	}

	@PutMapping("/predaje/{predmetId}")
	public Predaje izmeniPredmet(@PathVariable Integer predmetId, @RequestBody Predaje noviPredmet) {
		Predaje predmet = predajeRepository.findById(predmetId).get();
		if(predmet.getNazivPredmeta() != null) {
			predmet.setNazivPredmeta(noviPredmet.getNazivPredmeta());
		}
		if(predmet.getNedeljniFond() != null) {
			predmet.setNedeljniFond(noviPredmet.getNedeljniFond());
		}
		log.info("Predmet je izmenjen.");
		return predajeRepository.save(predmet);
	}

	@DeleteMapping("/predaje/{predajeId}")
	public void izbrisiPredmet(@PathVariable("predmetId") Integer predmetId) {
		log.info("Predmet je izbrisan.");
		predajeRepository.deleteById(predmetId);
	}

	@PostMapping("/predaje/{predajeId}/razredUcenika")
	public RazredUcenika kreirajRazredUcenika(@PathVariable("predajeId") Integer predajeId, @RequestBody RazredUcenikaDto razredUcenikaDto) {
		Predaje predaje = predajeRepository.findById(predajeId).get();
		RazredUcenika razredUcenika = new RazredUcenika();
		razredUcenika.setNaziv(razredUcenikaDto.getNaziv());
		razredUcenika.setPredaje(predaje);
		log.info("Razred je kreiran.");
		return razredUcenikaRepository.save(razredUcenika);
	}

	@PutMapping("/razredUcenika")
	public RazredUcenika izmeniRazredUcenika(@RequestBody RazredUcenika razredUcenika) {
		log.info("Naziv razreda je izmenjen.");
		return razredUcenikaRepository.save(razredUcenika);
	}

	@DeleteMapping("/razredUcenika/{razredUcenikaId}")
	public void izbrisiRazredUcenika(@PathVariable("razredUcenikaId") Integer razredUcenikaId) {
		log.info("Razred je izbrisan.");
		predajeRepository.deleteById(razredUcenikaId);
	}

	@PostMapping("/razredUcenika/{razredUcenikaId}/polugodiste")
	public Polugodiste kreirajPolugodiste(@PathVariable("razredUcenikaId") Integer razredUcenikaId, @RequestBody PolugodisteDto polugodisteDto) {
		RazredUcenika razredUcenika = razredUcenikaRepository.findById(razredUcenikaId).get();
		Polugodiste polugodiste = new Polugodiste();
		polugodiste.setNaziv(polugodisteDto.getNaziv());
		polugodiste.setRazredUcenika(razredUcenika);
		log.info("Polugodište dodato.");
		return polugodisteRepository.save(polugodiste);
	}

	@PutMapping("/polugodiste")
	public Polugodiste izmeniPolugosite(@RequestBody Polugodiste polugodiste) {
		log.info("Polugodište izmenjeno.");
		return polugodisteRepository.save(polugodiste);
	}

	@DeleteMapping("/polugodiste/{polugodisteId}")
	public void izbrisiPolugodiste(@PathVariable("polugodisteId") Integer razredUcenikaId) {
		log.info("Polugodište izbrisano.");
		predajeRepository.deleteById(razredUcenikaId);
	}

	@PostMapping("/polugodiste/{polugodisteId}/ocena/{ucenikId}/{ocena}/{profesorId}/{predmetId}")
	public Ocena kreirajOcenu(@PathVariable("polugodisteId") Integer polugodisteId, @PathVariable("ucenikId") Integer ucenikId,
			@PathVariable("ocena") Integer ocena,	@PathVariable("profesorId") Integer profesorId, @PathVariable("predmetId") Integer predmetId) {
		Profesor profesor = profesorRepository.findById(profesorId).get();
		Polugodiste polugodiste = polugodisteRepository.findById(polugodisteId).get();
		Ucenik ucenik = ucenikRepository.findById(ucenikId).get();
		Predaje predmet = predajeRepository.findById(predmetId).get();
		
		Ocena novaOcena = new Ocena();
		
		novaOcena.setOcena(ocena);
		novaOcena.setUcenik(ucenik);
		novaOcena.setPolugodiste(polugodiste);
		
		System.out.println(novaOcena.getOcena());
		
		
		EmailObject emailObject = new EmailObject();
		emailObject.setTo(ucenik.getRoditelj().getEmail());
		emailObject.setSubject(String.format("%s %s je dobio/la ocenu %s", ucenik.getPrezime(), ucenik.getIme(), novaOcena.getOcena()));
		emailObject.setText(String.format("Učenik %s %s je dobilo ocenu %s kod profesora %s %s iz predmeta %s.", ucenik.getIme(),
				ucenik.getPrezime(), novaOcena.getOcena(), profesor.getIme(), profesor.getPrezime(), predmet.getNazivPredmeta()));
		
		emailService.sendSimpleMessage(emailObject);
		log.info("Vaš mejl je poslat.");
		return ocenaRepository.save(novaOcena);
	}

	@PutMapping("/ocena/{ocenaId}")
	public Ocena izmeniOcenu(@PathVariable Integer ocenaId, @RequestBody Ocena novaOcena) {
		Ocena ocena = ocenaRepository.findById(ocenaId).get();
		if(novaOcena.getOcena() != null) {
			ocena.setOcena(novaOcena.getOcena());
		}
		log.info("Ocena je izmenjena.");
		return ocenaRepository.save(ocena);
	}

	@DeleteMapping("/ocena/{ocenaId}")
	public void izbrisiOcenu(@PathVariable("ocenaId") Integer ocenaId) {
		log.info("Izbrisana ocena");
		ocenaRepository.deleteById(ocenaId);
	}
}
