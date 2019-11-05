package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.District;

public interface DistrictRepository extends CrudRepository<District, String>{
	
	Optional<District> findById(String id);

	List<District> findAll(); 

}
