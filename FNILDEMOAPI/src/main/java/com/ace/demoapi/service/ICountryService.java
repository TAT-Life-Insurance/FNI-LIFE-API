package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;


import com.ace.demoapi.modal.Country;

public interface ICountryService {
	
	Optional<Country> findCountryById(String id);

	List<Country> findAllCountry();
	
	public void saveCountry(Country country);
	 
    public void deleteById(String id);

}
