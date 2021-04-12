package com.accela.test.candidate.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ADDRESS")
@Getter @Setter @NoArgsConstructor
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="STREET", nullable=false,  columnDefinition="VARCHAR(255)")
	private String street;
	
	@Column(name="CITY", nullable=false,  columnDefinition="VARCHAR(255)")
	private String city;
	
	@Column(name="STATE", nullable=false,  columnDefinition="VARCHAR(255)")
	private String state;
	
	@Column(name="POSTAL_CODE", nullable=false,  columnDefinition="VARCHAR(255)")
	private String postalCode;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PERSON_ID", nullable=false)
	private Person person;
	
}
