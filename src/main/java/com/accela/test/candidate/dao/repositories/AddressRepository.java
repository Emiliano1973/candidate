package com.accela.test.candidate.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.candidate.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
