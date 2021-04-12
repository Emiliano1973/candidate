package com.accela.test.candidate.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.accela.test.candidate.dao.AddressDao;
import com.accela.test.candidate.dtos.AddressDto;
import com.accela.test.candidate.entities.Address;
import com.accela.test.candidate.entities.Address_;
import com.accela.test.candidate.entities.Person;
import com.accela.test.candidate.entities.Person_;

@Repository
public class AddressDaoImpl implements AddressDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<AddressDto> findAllByPersonId(Long personId) {
		CriteriaBuilder cb=this.entityManager.getCriteriaBuilder();
		CriteriaQuery<AddressDto> addressCriteriaQuery=cb.createQuery(AddressDto.class);
		Root<Address> addressRoot=addressCriteriaQuery.from(Address.class);
		Join<Address, Person> personJoin=addressRoot.join(Address_.person, JoinType.INNER);
		addressCriteriaQuery.multiselect(addressRoot.get(Address_.id), personJoin.get(Person_.id), 
				addressRoot.get(Address_.street), addressRoot.get(Address_.city),
				addressRoot.get(Address_.state), addressRoot.get(Address_.postalCode))
				.where(cb.equal(personJoin.get(Person_.id), personId));
		TypedQuery<AddressDto> addressTypedQuery=this.entityManager.createQuery(addressCriteriaQuery);
		return addressTypedQuery.getResultList();
	}

	@Override
	public Optional<AddressDto> findById(Long addressId) {
		CriteriaBuilder cb=this.entityManager.getCriteriaBuilder();
		CriteriaQuery<AddressDto> addressCriteriaQuery=cb.createQuery(AddressDto.class);
		Root<Address> addressRoot=addressCriteriaQuery.from(Address.class);
		Join<Address, Person> personJoin=addressRoot.join(Address_.person, JoinType.INNER);
		addressCriteriaQuery.multiselect(addressRoot.get(Address_.id), personJoin.get(Person_.id), 
				addressRoot.get(Address_.street), addressRoot.get(Address_.city),
				addressRoot.get(Address_.state), addressRoot.get(Address_.postalCode))
				.where(cb.equal(addressRoot.get(Address_.ID), addressId));
		TypedQuery<AddressDto> addressTypedQuery=this.entityManager.createQuery(addressCriteriaQuery);
		return addressTypedQuery.getResultStream().findFirst();
	}

}
