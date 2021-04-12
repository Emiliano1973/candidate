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
import com.accela.test.candidate.dtos.AddressDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
classes = CandidateApplication.class)
@AutoConfigureMockMvc
public class AddressControllerTest {

	@Autowired
	private MockMvc mockMvc;
    
	
	private String addressString;

	private String adressStringUpdate;
	
	@BeforeEach
	public void setup() throws Exception {
		ObjectMapper objectMapper=new ObjectMapper();
		AddressDto address1P1=new AddressDto();
		address1P1.setStreet("Thor street");
		address1P1.setCity("Asgard city");
		address1P1.setState("Asgard");
		address1P1.setPostalCode("313");
		this.addressString= objectMapper.writeValueAsString(address1P1);
		
		AddressDto address2P1=new AddressDto();
		address2P1.setStreet("lOKI street");
		address2P1.setCity("Asgard city");
		address2P1.setState("Asgard");
		address2P1.setPostalCode("11");
		address2P1.setId(1L);
		address2P1.setPersonId(1L);
		this.adressStringUpdate= objectMapper.writeValueAsString(address2P1);
		
	}
	
	

	@Test
	public void whenGetAllAddressByPersoneIdThenReturnAdressesList() throws Exception{
		this.mockMvc.perform(get("/person/1/address")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.addresses").isArray());
	}


	@Test
	public void whenGetAddressByIdThenReturAddressObject() throws Exception{
		this.mockMvc.perform(get("/person/1/address/1")).andDo(print()).andExpect(status().isOk()).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void whenGetAdressByIdIsnotFoundThenReturNotFoundStatus() throws Exception{
		this.mockMvc.perform(get("/person/1/address/123")).andDo(print()).andExpect(status().isNotFound());
	}
	
	@Test
	public void whenAddNewAddressThenReturnCreatedStatus() throws Exception{
		this.mockMvc.perform(post("/person/1/address").content(this.addressString)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	
	@Test
	public void whenEditAddressThenReturnOkStatus() throws Exception{
		this.mockMvc.perform(put("/person/1/address/1").content(this.adressStringUpdate)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
	
	@Test
	public void whenDeleteAddressByIdThenReturnOkStatus() throws Exception{
		this.mockMvc.perform(delete("/person/2/address/3")).andDo(print()).andExpect(status().isOk());
	}

	
	
	
	
	
	
	
	
	
	
	

}
