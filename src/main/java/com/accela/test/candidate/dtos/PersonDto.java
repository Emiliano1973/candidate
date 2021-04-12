package com.accela.test.candidate.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PersonDto implements Serializable {
	
	private Long id;
	
	@NotBlank
	private String fistName;
	
	@NotBlank
	private String surname;
	
}
