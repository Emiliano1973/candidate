package com.accela.test.candidate.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.candidate.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
