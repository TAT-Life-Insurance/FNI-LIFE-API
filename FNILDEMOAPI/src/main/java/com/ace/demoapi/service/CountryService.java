package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ace.demoapi.modal.Country;
import com.ace.demoapi.repository.CountryRepository;

@Service
public class CountryService implements ICountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Optional<Country> findCountryById(String id) {
		
		return countryRepository.findById(id);
	}

	@Override
	public List<Country> findAllCountry() {
		
		return countryRepository.findAll();
	}

}
