package com.accela.test.candidate.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.accela.test.candidate.dao.PersonDao;
import com.accela.test.candidate.dtos.PersonDto;
import com.accela.test.candidate.entities.Person;
import com.accela.test.candidate.entities.Person_;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<PersonDto> findAll() {
		CriteriaBuilder cb=this.entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonDto> personCriteriaQuery=cb.createQuery(PersonDto.class);
		Root<Person> personRoot=personCriteriaQuery.from(Person.class);
		personCriteriaQuery.multiselect(personRoot.get(Person_.id), personRoot.get(Person_.firstName), personRoot.get(Person_.lastName)).orderBy(cb.asc(personRoot.get(Person_.lastName)), cb.asc(personRoot.get(Person_.firstName)));
		TypedQuery<PersonDto> personTypedQuery=this.entityManager.createQuery(personCriteriaQuery);		
		return personTypedQuery.getResultList();
	}

	@Override
	public Optional<PersonDto> findById(Long id) {
		CriteriaBuilder cb=this.entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonDto> personCriteriaQuery=cb.createQuery(PersonDto.class);
		Root<Person> personRoot=personCriteriaQuery.from(Person.class);
		personCriteriaQuery.multiselect(personRoot.get(Person_.id), personRoot.get(Person_.firstName), 
				personRoot.get(Person_.lastName))
		.where(cb.equal(personRoot.get(Person_.id), id));
		TypedQuery<PersonDto> personTypedQuery=this.entityManager.createQuery(personCriteriaQuery);		
		return personTypedQuery.getResultStream().findFirst();
	}

	

}
