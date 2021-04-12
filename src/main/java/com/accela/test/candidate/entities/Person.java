package com.accela.test.candidate.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PERSON")
@Getter @Setter @NoArgsConstructor
public class Person implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="FIST_NAME", nullable=false,  columnDefinition="VARCHAR(255)")
	private String firstName;

	@Column(name="LAST_NAME", nullable=false, columnDefinition="VARCHAR(255)")
	private String lastName;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="person", orphanRemoval=true)
	private Set<Address> addresses=new HashSet<>();
	
}
