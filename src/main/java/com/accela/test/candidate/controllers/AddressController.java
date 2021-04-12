package com.accela.test.candidate.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accela.test.candidate.dtos.AddressDto;
import com.accela.test.candidate.services.AddressService;

@RestController
@RequestMapping("/person/{personId}/address")
public class AddressController {

	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService=addressService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllByPersonId(@PathVariable("personId") Long personId){
		List<AddressDto> addressDtos=this.addressService.findAllByPersonId(personId);
		Map<String, List<AddressDto>> addressMaps=new HashMap<>(1);
		addressMaps.put("addresses",addressDtos);
		return new ResponseEntity<>(addressMaps, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{addressId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable("addressId") Long addressId){
		Optional<AddressDto> addressOpt=this.addressService.findById(addressId);
		if(addressOpt.isPresent()) {
		return new ResponseEntity<>(addressOpt.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping( consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@PathVariable("personId") Long personId, @RequestBody AddressDto addressDto){
		this.addressService.add(personId, addressDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{addressId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@PathVariable("addressId") Long addressId, @RequestBody AddressDto addressDto){
		this.addressService.edit(addressId, addressDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{addressId}")
	public ResponseEntity<?> delete(@PathVariable("addressId") Long addressId){
		this.addressService.delete(addressId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
