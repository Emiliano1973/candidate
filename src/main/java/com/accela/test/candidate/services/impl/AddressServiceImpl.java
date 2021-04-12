package com.accela.test.candidate.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.accela.test.candidate.dao.AddressDao;
import com.accela.test.candidate.dao.repositories.AddressRepository;
import com.accela.test.candidate.dao.repositories.PersonRepository;
import com.accela.test.candidate.dtos.AddressDto;
import com.accela.test.candidate.entities.Address;
import com.accela.test.candidate.entities.Person;
import com.accela.test.candidate.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressDao addressDao;
	private final AddressRepository addressRepository;
	private final PersonRepository personRepository;
 
	
	public AddressServiceImpl(AddressDao addressDao, AddressRepository addressRepository,
			PersonRepository personRepository) {
		super();
		this.addressDao = addressDao;
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
	}

	@Override
	@Transactional
	public void add(Long personId, AddressDto addressDto) {
		Person person=this.personRepository.findById(personId).orElseThrow(()-> new RuntimeException("Person not found for id:"+personId));
		Address address=new Address();
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setPostalCode(addressDto.getPostalCode());
		address.setPerson(person);
		this.addressRepository.save(address);
	}

	@Override
	@Transactional
	public void edit(Long addressId, AddressDto addressDto) {
		Address address=this.addressRepository.findById(addressId).orElseThrow(()-> new RuntimeException("Address not found for id:"+addressId));
		address.setStreet(addressDto.getStreet());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setPostalCode(addressDto.getPostalCode());
	}

	@Override
	@Transactional
	public void delete(Long addressId) {
		Address address=this.addressRepository.findById(addressId).orElseThrow(()-> new RuntimeException("Address not found for id:"+addressId));
	   this.addressRepository.delete(address);
	}

	@Override
	public List<AddressDto> findAllByPersonId(Long personId) {
		return this.addressDao.findAllByPersonId(personId);
	}

	@Override
	public Optional<AddressDto> findById(Long addressId) {
		return this.addressDao.findById(addressId);
	}

}
