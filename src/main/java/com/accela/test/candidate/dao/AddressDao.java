package com.accela.test.candidate.dao;

import java.util.List;
import java.util.Optional;

import com.accela.test.candidate.dtos.AddressDto;

public interface AddressDao {
	
	List<AddressDto> findAllByPersonId(Long personId);
	
	Optional<AddressDto> findById(Long addressId);

}
