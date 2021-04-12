package com.accela.test.candidate.dao;

import java.util.List;
import java.util.Optional;

import com.accela.test.candidate.dtos.PersonDto;

public interface PersonDao {
	
	List<PersonDto> findAll();
	
	Optional<PersonDto> findById(Long id);
}
