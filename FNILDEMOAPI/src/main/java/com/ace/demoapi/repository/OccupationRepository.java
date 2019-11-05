package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Occupation;

public interface OccupationRepository  extends CrudRepository<Occupation, String>{
	

	Optional<Occupation> findById(String id);

	List<Occupation> findAll();


}
