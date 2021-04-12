package com.accela.test.candidate.services;

import java.util.List;
import java.util.Optional;

import com.accela.test.candidate.dtos.PersonDto;

public interface PersonService {

  Optional<PersonDto> findById(Long personId);
  
  List<PersonDto> findAll();
  
  void add(PersonDto personDto);
  
  void edit(Long personId,PersonDto personDto);
  
  void delete(Long personId);
  
  Long count();
}
