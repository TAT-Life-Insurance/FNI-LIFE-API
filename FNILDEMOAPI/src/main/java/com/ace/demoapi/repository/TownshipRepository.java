package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Township;

public interface TownshipRepository extends CrudRepository<Township, String>{
	
	Optional<Township> findById(String id);

	List<Township> findAll();


}
