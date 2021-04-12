package com.accela.test.candidate.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.accela.test.candidate.CandidateApplication;
import com.accela.test.candidate.dtos.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
  classes = CandidateApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
    
	
	private String personString;

	private String personStringUpdate;
	
	@BeforeEach
	public void setup() throws Exception {
		ObjectMapper objectMapper=new ObjectMapper();
		PersonDto person=new PersonDto();
		person.setFistName("Carol");
		person.setSurname("Danvers");
		this.personString= objectMapper.writeValueAsString(person);
		
		
		PersonDto person2=new PersonDto();
		person2.setFistName("Bruce");
		person2.setSurname("Wayne");
		person2.setId(1L);;
		this.personStringUpdate= objectMapper.writeValueAsString(person2);
		
	}
	
	@Test
	public void whenGetAllPeopleThenReturnPeopleList() throws Exception{
		this.mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk()).andExpect(status().isOk())
		.andExpect(jsonPath("$.people").isArray());
	}


	@Test
	public void whenGetPersonByIdThenReturPersonObject() throws Exception{
		this.mockMvc.perform(get("/person/1")).andDo(print()).andExpect(status().isOk()).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void whenGetPersonByIdIsnotThenReturNotFoundStatus() throws Exception{
		this.mockMvc.perform(get("/person/105")).andDo(print()).andExpect(status().isNotFound());
	}
	@Test
	public void whenGetPersonountThenReturnPeopleNumber() throws Exception{
		this.mockMvc.perform(get("/person/count")).andDo(print()).andExpect(status().isOk()).andExpect(status().isOk())
		.andExpect(jsonPath("$.peopleCount").isNumber());
	}

	
	
	@Test
	public void whenAddNewPersonThenReturnCreatedStatus() throws Exception{
		this.mockMvc.perform(post("/person").content(this.personString)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	
	@Test
	public void whenEditPersonThenReturnOkStatus() throws Exception{
		this.mockMvc.perform(put("/person/1").content(this.personStringUpdate)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	public void whenDeletePersonByIdThenReturnOkStatus() throws Exception{
		this.mockMvc.perform(delete("/person/2")).andDo(print()).andExpect(status().isOk());
	}
	
	
}
