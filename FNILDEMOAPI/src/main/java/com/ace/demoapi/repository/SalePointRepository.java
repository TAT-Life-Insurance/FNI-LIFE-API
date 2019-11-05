package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.SalesPoints;

public interface SalePointRepository extends CrudRepository<SalesPoints, String> {
	
	
	Optional<SalesPoints> findById(String id);

	List<SalesPoints> findAll();


}
