package com.accela.test.candidate.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accela.test.candidate.dtos.PersonDto;
import com.accela.test.candidate.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final  PersonService personService;
	
	public PersonController(PersonService personService) {
		super();
		this.personService=personService;
	}
	
	@GetMapping(value="/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> count(){
		Long contPeople=this.personService.count();
		Map<String, Long> countMap=new HashMap<>(1);
		countMap.put("peopleCount",contPeople);
		return new ResponseEntity<>(countMap, HttpStatus.OK );
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll(){
		List<PersonDto> persons=this.personService.findAll();
		Map<String, List<PersonDto>> personMaps=new HashMap<>(1);
		personMaps.put("people",persons);
		return new ResponseEntity<>(personMaps, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{personId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable("personId") Long personId){
		Optional<PersonDto> persoOptional=this.personService.findById(personId);
		if(persoOptional.isPresent()) {
		return new ResponseEntity<>(persoOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping( consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add( @RequestBody PersonDto personDto){
		this.personService.add(personDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(value="/{personId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@PathVariable("personId") Long personId, @RequestBody PersonDto personDto){
		this.personService.edit(personId, personDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@DeleteMapping(value="/{personId}")
	public ResponseEntity<?> delete(@PathVariable("personId") Long personId){
		this.personService.delete(personId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
