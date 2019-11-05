package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Industry;

public interface IndustryRepository extends CrudRepository<Industry, String>{
	
	Optional<Industry> findById(String id);

	List<Industry> findAll();

}
