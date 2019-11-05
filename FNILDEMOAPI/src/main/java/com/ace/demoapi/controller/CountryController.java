package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Country;
import com.ace.demoapi.service.ICountryService;

@RestController
public class CountryController {
	

	@Autowired
	private ICountryService countryService;

	@GetMapping(path = "/countries", produces = "application/json")
	public List<Country> getAllCountry() {
		return countryService.findAllCountry();
	}

	@GetMapping(path = "/country/{id}", produces = "application/json")
	public Country getCountryById(@PathVariable String id) {
		return countryService.findCountryById(id).orElse(null);
	}
	
	@PostMapping(path = "/saveCountry")
	public Country saveCountry(@RequestBody Country country) {
		countryService.saveCountry(country);
		return country;
	}
	
	
	@DeleteMapping("/deleteCountry/{id}")
	public void deleteById(@PathVariable String id){
		countryService.deleteById(id);
		
	}


}
