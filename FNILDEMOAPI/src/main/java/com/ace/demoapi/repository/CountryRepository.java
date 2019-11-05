package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Country;

public interface CountryRepository  extends CrudRepository<Country, String>{
	
	Optional<Country> findById(String id);

	List<Country> findAll();
}