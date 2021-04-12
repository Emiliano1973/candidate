package com.accela.test.candidate.services;

import java.util.List;
import java.util.Optional;

import com.accela.test.candidate.dtos.AddressDto;

public interface AddressService {

	void add(Long personId, AddressDto addressDto);
	
	void edit(Long addressId, AddressDto addressDto);
	
	void delete(Long addressId);
	
	List<AddressDto> findAllByPersonId(Long personId);
	
	Optional<AddressDto> findById(Long addressId);
	
}
