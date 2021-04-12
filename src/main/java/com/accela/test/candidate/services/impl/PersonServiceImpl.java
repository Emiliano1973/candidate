package com.accela.test.candidate.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.accela.test.candidate.dao.PersonDao;
import com.accela.test.candidate.dao.repositories.PersonRepository;
import com.accela.test.candidate.dtos.PersonDto;
import com.accela.test.candidate.entities.Person;
import com.accela.test.candidate.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonDao personDao;
	private final PersonRepository personRepository;
	
	public PersonServiceImpl(PersonDao personDao, PersonRepository personRepository) {
		super();
		this.personDao = personDao;
		this.personRepository = personRepository;
	}
	
	@Override
	public Optional<PersonDto> findById(Long id) {
		return this.personDao.findById(id);
	}
	
	@Override
	public List<PersonDto> findAll() {
		return this.personDao.findAll();
	}
	
	@Override
	@Transactional
	public void add(PersonDto personDto) {
		Person person=new Person();
		person.setFirstName(personDto.getFistName());
		person.setLastName(personDto.getSurname());
		this.personRepository.save(person);
	}

	@Override
	@Transactional
	public void edit(Long personId, PersonDto personDto) {
		Person person=this.personRepository.findById(personId).orElseThrow(()-> new RuntimeException("Person not found for id:"+personId));
		person.setFirstName(personDto.getFistName());
		person.setLastName(personDto.getSurname());
	}

	@Override
	@Transactional
	public void delete(Long personId) {
		Person person=this.personRepository.findById(personId).orElseThrow(()-> new RuntimeException("Person not found for id:"+personId));
		this.personRepository.delete(person);
	}

	@Override
	@Transactional
	public Long count() {
		return Long.valueOf(this.personRepository.count());
	}
}
