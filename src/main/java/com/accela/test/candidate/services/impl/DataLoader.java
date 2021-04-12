package com.accela.test.candidate.services.impl;

import javax.transaction.Transactional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.accela.test.candidate.dao.repositories.AddressRepository;
import com.accela.test.candidate.dao.repositories.PersonRepository;
import com.accela.test.candidate.entities.Address;
import com.accela.test.candidate.entities.Person;

@Component
public class DataLoader implements ApplicationRunner {

	private final AddressRepository addressRepository;
	private final PersonRepository personRepository;

	public DataLoader(AddressRepository addressRepository, PersonRepository personRepository) {
		super();
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
	}


	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		Person person1=new Person();
		person1.setFirstName("Donald");
		person1.setLastName("Duck");
		this.personRepository.save(person1);
		Address address1P1=new Address();
		address1P1.setStreet("Duck street");
		address1P1.setCity("DuckBurg");
		address1P1.setState("Calisota");
		address1P1.setPostalCode("313");
		address1P1.setPerson(person1);
		this.addressRepository.save(address1P1);
		
		Address address1P2=new Address();
		address1P2.setStreet("Mouse street");
		address1P2.setCity("MOuseTownb");
		address1P2.setState("Calisota");
		address1P2.setPostalCode("312");
		address1P2.setPerson(person1);
		this.addressRepository.save(address1P2);
		
		Person person2=new Person();
		person2.setFirstName("Charles");
		person2.setLastName("Xavier");
		this.personRepository.save(person2);
		Address address2P1=new Address();
		address2P1.setStreet("Mutant street");
		address2P1.setCity("Krakoa city");
		address2P1.setState("Krakoa");
		address2P1.setPostalCode("313");
		address2P1.setPerson(person2);
		this.addressRepository.save(address2P1);
		
		Address address2P2=new Address();
		address2P2.setStreet("Wolverine street");
		address2P2.setCity("New York city");
		address2P2.setState("New York");
		address2P2.setPostalCode("316");
		address2P2.setPerson(person2);
		this.addressRepository.save(address2P2);
	}

}
